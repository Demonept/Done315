package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.dao.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
   private final UserDao userDao;

   @Autowired
   public UserServiceImp(UserDao userDao){
      this.userDao = userDao;
   }

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> getUsersList() {
      return userDao.getUsersList();
   }

   @Transactional
   @Override
   public void update(User user) {
      userDao.update(user);
   }

   @Transactional(readOnly = true)
   @Override
   public User getUser(Long id) {
      return userDao.getUser(id);
   }
   @Transactional
   @Override
   public void deleteUser(Long id) {
      userDao.deleteUser(id);
   }

}
