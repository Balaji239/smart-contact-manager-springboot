package com.smartcontactmanager.controller;

import com.smartcontactmanager.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // @GetMapping("/dashboard")
    // public String userDashboardView(HttpSession session, Principal principal){
    //     session.setAttribute("userLoggedin","true");
    //     session.setAttribute("user", userService.getUserByName(principal.getName()));
    //     return "redirect:/";
    // }

    @GetMapping("/home")
    public String userDashboardView(HttpSession session, Principal principal){
        session.setAttribute("userLoggedin","true");
        session.setAttribute("user", userService.getUserByName(principal.getName()));
        return "/normal-user/home";
    }

}
