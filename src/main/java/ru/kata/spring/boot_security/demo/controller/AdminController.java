package ru.kata.spring.boot_security.demo.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.service.SecurityUserService;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private SecurityUserService userService;

    @Autowired
    public AdminController(SecurityUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getAllUsersPage(ModelMap model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/adduser")
    public String getAddUserPage(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        return "adduser";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin/";
    }


    @GetMapping("/user/{id}")
    public String getUserPage(@PathVariable Long id, ModelMap model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "edituser";
    }

    @PatchMapping("/admin/user/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/";
    }
}