package com.smartcontactmanager.controller;

import com.smartcontactmanager.entities.UserRegisterForm;
import com.smartcontactmanager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping("/do_register")
    public String doRegister(@Valid @ModelAttribute("userRegForm") UserRegisterForm registerForm, BindingResult bindingResult){
        System.out.println("Calling register controller");
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());
            return "public/signup";
        }
        System.out.println("No errors :");
        if(!userService.registerUser(registerForm).equals("")){
            System.out.println("Inside object error");
            bindingResult.addError(new FieldError("email", "email", "User already exists"));
        }
        return "public/signup";
    }
}
