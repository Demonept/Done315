package ru.kata.spring.boot_security.demo.dao.dao;

import ru.kata.spring.boot_security.demo.model.User;
import javax.persistence.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @PersistenceContext
   private EntityManager em;

   @Override
   public void add(User user) {
      em.persist(user);
   }

   @Override
   public List<User> getUsersList() {
      return em.createQuery("From User").getResultList();
   }

   @Override
   public void update(User user) {
      em.merge(user);
   }

   @Override
   public User getUser(Long id) {
      User user = em.find(User.class, id);
      return user;
   }

   @Override
   public void deleteUser(Long id) {
      em.remove(em.find(User.class, id));
   }

}
