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
public class DashboardController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @GetMapping("/add-contact")
    public String addContactView(Model model){
        model.addAttribute("contact", new Contact());
        return "normal-user/add_contact";
    }

    @PostMapping("/process-contact")
    public String processContact(@Valid @ModelAttribute Contact contact, BindingResult bindingResult, @RequestParam("profileImage") MultipartFile profileImg, Principal principal, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "normal-user/add_contact";
        }
        if(contactService.addContact(principal.getName(), contact, profileImg)){
            redirectAttributes.addFlashAttribute("status","success");
            return "redirect:/user/add-contact";
        }
        redirectAttributes.addFlashAttribute("status","fail");
        return "redirect:/user/add-contact";
    }


    @GetMapping("/view-contacts/{page}")
    public String showContactsPage(@PathVariable("page")int page, Model model, HttpSession session,
                                   @RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir,Principal principal){
        Page<Contact> contacts = contactService.findPaginatedContactsList(((User) session.getAttribute("user")), page,5, sortField, sortDir);
        session.setAttribute("userLoggedin","true");
        session.setAttribute("user", userService.getUserByName(principal.getName()));
        model.addAttribute("contacts", contacts);
        model.addAttribute("currentPage", page);
        System.out.println("Current page : "+page);
        model.addAttribute("totalPages", contacts.getTotalPages());
        System.out.println("totalPages : "+contacts.getTotalPages());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
//        model.addAttribute("isDeleted", true);
        return "normal-user/view_contacts";
    }

    @GetMapping("/contact/{id}")
    public String singleContactView(@PathVariable("id")int contactId, Model model, HttpSession session){
        Contact contact = contactService.getContactById(contactId).get();
        if( ((User)session.getAttribute("user")).getId() == contact.getUser().getId()){
            model.addAttribute("contact", contact);
        }
        return "normal-user/contact_details";
    }

    @GetMapping("/delete-contact/{id}")
    public String deleteContact(@PathVariable("id")int contactId, HttpSession session, RedirectAttributes redirectAttributes){
        Contact contact = contactService.getContactById(contactId).get();
        if( ((User)session.getAttribute("user")).getId() == contact.getUser().getId()){
            contactService.deleteContactById(contactId);
            redirectAttributes.addFlashAttribute("isDeleted", true);
        }
        return "redirect:/user/view-contacts/0?sortField=name&sortDir=asc";
    }

    @GetMapping("/edit-contact/{id}")
    public String editContactView(@PathVariable("id") int contactId, Model model){
        Contact contact = contactService.getContactById(contactId).get();
        model.addAttribute("contact", contact);
        return "normal-user/edit_contact";
    }
}