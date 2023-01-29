package ru.kata.spring.boot_security.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.SecurityUser;

import java.security.Principal;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/rest")
public class RestControllerA {

    PasswordEncoder bCryptPasswordEncoder;
    private SecurityUser securityUser;
    private RoleService roleService;
    @Autowired
    public RestControllerA(SecurityUser securityUser, RoleService roleService, PasswordEncoder passwordEncoder){
        this.roleService = roleService;
        this.securityUser = securityUser;
        this.bCryptPasswordEncoder = passwordEncoder;
    }


    @GetMapping("/")
    public ResponseEntity<String> hello(){
        return new ResponseEntity<>("Hello world", HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> showAllUsers(){
        return new ResponseEntity<>(securityUser.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> showTheUser(@PathVariable Long id){
        User user = securityUser.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @GetMapping("/users/role/{id}")
    public ResponseEntity<Role> showTheRole(@PathVariable Long id){
        Role role = roleService.findBy(id);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }
    @GetMapping("/users/authentication")
    public ResponseEntity<UserDetails> getAuthUser(){
        User user = securityUser.getAuthUser();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteTheUser(@PathVariable Long id){
        securityUser.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/users")
    public ResponseEntity<HttpStatus> addNewUser(@RequestBody User user){
        securityUser.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/users")
    public ResponseEntity<HttpStatus> editUser(@RequestBody User user){
        securityUser.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
