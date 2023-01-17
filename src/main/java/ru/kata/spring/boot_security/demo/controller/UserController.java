package ru.kata.spring.boot_security.demo.controller;


import ru.kata.spring.boot_security.demo.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.service.SecurityUserService;

import java.security.Principal;


@Controller
@RequestMapping("/user")
public class UserController {

    private UserRepository userRepository;
    private SecurityUserService userService;
    @Autowired
    public UserController(SecurityUserService userService){
        this.userService = userService;
    }
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/info")
    public String getUserPage(Principal principal, ModelMap model){
        User user = userRepository.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "userinfo";
    }
}