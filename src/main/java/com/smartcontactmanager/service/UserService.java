package com.smartcontactmanager.service;

import com.smartcontactmanager.entities.User;
import com.smartcontactmanager.entities.UserRegisterForm;
import com.smartcontactmanager.repository.UserRepository;
import com.smartcontactmanager.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private FileUtils fileUtils;


    public User getUserByName(String name){
        return userRepository.findUserByFirstName(name);
    }

    public String registerUser(UserRegisterForm registerForm){

        if(userRepository.findUserByEmail(registerForm.getEmail())!=null){
            return "User with that email already exists";
        }

        User user = new User();
        System.out.println("File name is "+ registerForm.getProfileImg().getOriginalFilename());
        user.setFirstName(registerForm.getFirstName());
        user.setLastName(registerForm.getLastName());
        user.setEmail(registerForm.getEmail());
        user.setContactNumber(registerForm.getContactNumber());
        user.setPassword(passwordEncoder.encode(registerForm.getPassword()));
        user.setGender(registerForm.getGender());
        user.setDob(registerForm.getDob());
        user.setCreatedBy(user.getFirstName());
        if(registerForm.getProfileImg() != null){
            user.setProfileImg(registerForm.getProfileImg().getOriginalFilename());
            fileUtils.saveImage(registerForm.getProfileImg());
        }
        userRepository.save(user);
        return "";
    }
}
