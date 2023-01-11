package ru.kata.spring.boot_security.demo.controller;


import ru.kata.spring.boot_security.demo.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public String getUserPage(@PathVariable Long id, ModelMap model){
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "edituser";
    }
    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user){
        userService.update(user);
        return "redirect:/";
    }
}