package ru.kata.spring.boot_security.demo.configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(RoleRepository roleRepository,
                               UserRepository userRepository,
                               PasswordEncoder passwordEncoder) {
        return args -> {
            Role roleAdmin = new Role("ROLE_ADMIN");
            Role roleUser = new Role("ROLE_USER");

            roleRepository.save(roleAdmin);
            roleRepository.save(roleUser);

            Set adminRoleArray = new HashSet<>();
            adminRoleArray.add(roleAdmin);
            adminRoleArray.add(roleUser);
            Set userRoleArray = new HashSet<>();
            userRoleArray.add(roleUser);

            userRepository.save(new User("Admin", "Boss", "Just email@",
                    (passwordEncoder.encode("admin")),adminRoleArray));

            userRepository.save(new User("User", "user", "email@",
                    (passwordEncoder.encode("123")),userRoleArray));

            userRepository.save(new User("Dmitriy", "Novak", "SomeBody@",
                    (passwordEncoder.encode("1122")),userRoleArray));
        };
    }
}