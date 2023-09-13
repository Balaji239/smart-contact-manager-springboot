package com.smartcontactmanager.controller;

import com.smartcontactmanager.entities.UserRegisterForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = {"/home","/"})
    public String homeView(){
        return "public/index";
    }

    @GetMapping("/signup")
    public String signupView(Model model){
        model.addAttribute("userRegForm", new UserRegisterForm());
        return "public/signup";
    }

    @GetMapping("/signin")
    public String loginView(){
        return "public/login";
    }

    @GetMapping("/about")
    public String aboutView(){
        return "public/about";
    }

}
