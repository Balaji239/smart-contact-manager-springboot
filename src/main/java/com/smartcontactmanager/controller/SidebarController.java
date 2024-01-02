package com.smartcontactmanager.controller;

import com.smartcontactmanager.entities.Contact;
import com.smartcontactmanager.entities.User;
import com.smartcontactmanager.service.ContactService;
import com.smartcontactmanager.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class SidebarController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @GetMapping("/add-contact")
    public String addContactView(Model model){
        model.addAttribute("contact", new Contact());
        return "normal-user/add_contact";
    }

    @GetMapping("/view-contacts/{page}")
    public String showContactsPage(@PathVariable("page")int page, Model model, HttpSession session,
                                   @RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir, @RequestParam(required = false) String filter,
                                   @RequestParam(required = false) String searchTerm, Principal principal){
        Page<Contact> contacts;
        session.setAttribute("userLoggedin","true");
        session.setAttribute("user", userService.getUserByName(principal.getName()));
        User user = (User) session.getAttribute("user");
        model.addAttribute("filter","All Contacts");
        if(searchTerm!=null){
            contacts = contactService.findContactContainingName(searchTerm, user,page,5,sortField,sortDir);
            model.addAttribute("searchTerm",searchTerm);
        }
        else if(filter!=null){
            if(filter.equalsIgnoreCase("All Contacts")){
                contacts = contactService.findPaginatedContactsList(user, page,5, sortField, sortDir);
            }
            else{
                contacts = contactService.findPaginatedContactsByRelationship(filter,user, page,5,sortField,sortDir);
            }
            model.addAttribute("filter",filter);
        }
        else {
            System.out.println("My name is Balaji");
            contacts = contactService.findPaginatedContactsList(user, page,5, sortField, sortDir);
        }

        model.addAttribute("contacts", contacts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", contacts.getTotalPages());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        return "normal-user/view_contacts";
    }

    @GetMapping("/view-profile")
    public String viewProfile(Model model, HttpSession session){
        model.addAttribute("user", session.getAttribute("user"));
        return "normal-user/view_profile";
    }

}
