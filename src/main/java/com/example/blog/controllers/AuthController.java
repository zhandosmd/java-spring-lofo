package com.example.blog.controllers;

import com.example.blog.models.Role;
import com.example.blog.models.User;
import com.example.blog.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
      Model model,
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
            model.addAttribute("message", "User exists!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return "redirect:/login";
    }
}
