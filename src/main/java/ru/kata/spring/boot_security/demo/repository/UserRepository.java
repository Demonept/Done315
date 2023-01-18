package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u join fetch u.roles where u.username = :username")
    User findByUsername(String username);

    @Modifying
    @Query("update User u set u.username = ?1, u.lastName =?2, u.email=?3 where u.id = ?4")
    void updateUser(String username, String lastname, String email, Long userId);
}
