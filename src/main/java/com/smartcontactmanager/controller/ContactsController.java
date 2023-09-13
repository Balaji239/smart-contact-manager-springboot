package com.smartcontactmanager.controller;

import com.smartcontactmanager.entities.Contact;
import com.smartcontactmanager.service.ContactService;
import com.smartcontactmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class ContactsController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @GetMapping("/con-search/{searchTerm}")
    public String searchContact(@PathVariable("searchTerm")String searchTerm, Principal principal, Model model){
       List<Contact> contacts = contactService.findContactContainingName(searchTerm, userService.getUserByName(principal.getName()));
       model.addAttribute("contacts", contacts);
       return "normal-user/contact_search_result";
    }
}
