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
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class ContactsController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @GetMapping("/con-search/{searchTerm}/{page}")
    public String searchContact(@PathVariable("page") int page, @RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir, @PathVariable("searchTerm") String searchTerm, Principal principal, Model model) {
        Page<Contact> contacts = contactService.findContactContainingName(searchTerm, userService.getUserByName(principal.getName()), page, 5, sortField, sortDir);
        model.addAttribute("contacts", contacts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", contacts.getTotalPages());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("searchTerm", searchTerm);
        return "normal-user/contact_search_result";
    }

    @GetMapping("/contact/{id}")
    public String singleContactView(@PathVariable("id") int contactId, Model model, HttpSession session) {
        Contact contact = contactService.getContactById(contactId).get();
        if (((User) session.getAttribute("user")).getId() == contact.getUser().getId()) {
            model.addAttribute("contact", contact);
        }
        return "normal-user/contact_details";
    }

    @GetMapping("/delete-contact/{id}")
    public String deleteContact(@PathVariable("id") int contactId, HttpSession session, RedirectAttributes redirectAttributes) {
        Contact contact = contactService.getContactById(contactId).get();
        if (((User) session.getAttribute("user")).getId() == contact.getUser().getId()) {
            contactService.deleteContactById(contactId);
            redirectAttributes.addFlashAttribute("isDeleted", true);
        }
        return "redirect:/user/view-contacts/0?sortField=name&sortDir=asc";
    }

    @GetMapping("/edit-contact/{id}")
    public String editContactView(@PathVariable("id") int contactId, Model model) {
        Contact contact = contactService.getContactById(contactId).get();
        model.addAttribute("contact", contact);
        return "normal-user/edit_contact";
    }

    @PostMapping("/process-contact")
    public String processContact(@Valid @ModelAttribute Contact contact, BindingResult bindingResult, @RequestParam("profileImage") MultipartFile profileImg, Principal principal, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "normal-user/add_contact";
        }
        if (contactService.addContact(principal.getName(), contact, profileImg)) {
            redirectAttributes.addFlashAttribute("status", "success");
            return "redirect:/user/add-contact";
        }
        redirectAttributes.addFlashAttribute("status", "fail");
        return "redirect:/user/add-contact";
    }

    @PostMapping("/con-favourite")
    @ResponseBody
    public String updateFavourite(@RequestParam int conId, @RequestParam boolean isFavourite) {
        contactService.updateFavourite(isFavourite, conId);
        return "Updated favourite";
    }

    @PostMapping("/process-update-contact")
    public String updateContact(@RequestParam int conId, @Valid @ModelAttribute Contact contact, BindingResult bindingResult, RedirectAttributes redirectAttributes, @RequestParam MultipartFile profileImage) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "normal-user/edit_contact";
        }
        Contact cont = contactService.getContactById(conId).get();
        cont.setName(contact.getName());
        cont.setEmail(contact.getEmail());
        cont.setPhone(contact.getPhone());
        cont.setDescription(contact.getDescription());
        cont.setRelationship(contact.getRelationship());
        if (contactService.updateContact(cont, profileImage))
            redirectAttributes.addFlashAttribute("status", "success");
        else
            redirectAttributes.addFlashAttribute("status", "fail");

        return "redirect:/user/edit-contact/"+conId;
    }

}
