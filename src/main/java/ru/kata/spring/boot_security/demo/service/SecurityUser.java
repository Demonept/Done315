package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface SecurityUser {
    public UserDetails loadUserByUsername(String username);
    public User findUserById(Long userId);
    public List<User> getAllUsers();
    public boolean saveUser(User user);
    public boolean deleteUser(Long userId);
    public void updateUser(User user);
    public User getUser(long id);
}
