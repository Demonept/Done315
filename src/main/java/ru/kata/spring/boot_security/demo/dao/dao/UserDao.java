package ru.kata.spring.boot_security.demo.dao.dao;

import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;

public interface UserDao {
   void add(User user);
   List<User> getUsersList();
   void update(User user);

   User getUser(Long id);

   void deleteUser(Long id);
}
