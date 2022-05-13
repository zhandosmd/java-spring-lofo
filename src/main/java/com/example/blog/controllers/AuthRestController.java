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
        @RequestParam String password,
        @RequestParam(required=false) String name,
        @RequestParam(required=false) String surname,
        @RequestParam(required=false) String faculty,
        @RequestParam(required=false) String email,
        @RequestParam(required=false) String phone
        ){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        user.setSurname(surname);
        user.setFaculty(faculty);
        user.setEmail(email);
        user.setPhone(phone);
        User userFromDb = userRepository.findByUsername(user.getUsername());
        if(userFromDb!=null){
            return "user by this username exists!";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return "successfully registered";
    }

    @PostMapping("/api/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password
    ){
        User userFromDb = userRepository.findByUsername(username);
        if(userFromDb == null){
            return "invalid username";
        }
        if(!userFromDb.getPassword().equals(password)){
            return "invalid password";
        }
        userFromDb.setActive(true);
        userRepository.save(userFromDb);
        return "valid user";
    }
}
