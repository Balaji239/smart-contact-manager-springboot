package com.smartcontactmanager.controller;

import com.smartcontactmanager.entities.UserRegisterForm;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = {"/home","/"})
    public String homeView(Model model,HttpSession session){
        Boolean isUserLoggedIn = Boolean.parseBoolean((String) session.getAttribute("userLoggedin"));
        if(isUserLoggedIn!=null && isUserLoggedIn){
            model.addAttribute("userLoggedin",true);
        }
        else{
            model.addAttribute("userLoggedin",false);
        }
        return "public/index";
    }

    @GetMapping("/about")
    public String aboutView(){
        return "public/about";
    }

    @GetMapping("/signup")
    public String signupView(Model model){
        model.addAttribute("userRegForm", new UserRegisterForm());
        return "public/signup";
    }

}
