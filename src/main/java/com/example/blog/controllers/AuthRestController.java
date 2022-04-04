package com.example.blog.controllers;

import com.example.blog.models.Role;
import com.example.blog.models.User;
import com.example.blog.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class AuthRestController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/api/registration")
    public String addUser(
        @RequestParam String username,
        @RequestParam String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        User userFromDb = userRepository.findByUsername(user.getUsername());
        if(userFromDb!=null){
            return "User by this username exists!";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return "Succesfully registrated";
    }
}
