package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.SecurityUser;
import ru.kata.spring.boot_security.demo.service.SecurityUserService;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {


    PasswordEncoder bCryptPasswordEncoder;

    private SecurityUser userService;
    private RoleService roleService;

    @Autowired
    public AdminController(SecurityUser userService,
                           RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String getAllUsersPage(Principal principal, ModelMap model) {
        model.addAttribute("main",userService.loadUserByUsername(principal.getName()));
        model.addAttribute("newUser", new User());
        model.addAttribute("users",  userService.getAllUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        return "users";
    }

    @GetMapping("/adduser")
    public String getAddUserPage(ModelMap model) {
        model.addAttribute("user",new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "adduser";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin/";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.updateUser(user);
        return "redirect:/admin/";
    }

    @GetMapping("/user/{id}")
    public String getUserPage(@PathVariable Long id, ModelMap model){
        User user = userService.getUser(id);
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("user", user);
        return "edituser";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/";
    }
}