package ru.kata.spring.boot_security.demo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements UserDetails {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "name")
   private String username;

   @Column(name = "last_name")
   private String lastName;

   @Column(name = "email")
   private String email;

   @Column(name = "password")
   private String password;

   @ManyToMany(fetch = FetchType.LAZY)
   @JoinTable(
           name = "user_role"
           , joinColumns = @JoinColumn(name = "user_id")
           , inverseJoinColumns = @JoinColumn(name = "role_name")
   )
   private List<Role> role;

   public void setPassword(String password) {
      this.password = password;
   }

   public User() {}
   
   public User(String firstName, String lastName, String email, String password) {
      this.username = firstName;
      this.lastName = lastName;
      this.email = email;
      this.password = password;
   }

   public List<Role> getRole() {
      return role;
   }

   public void setRole(Role role) {
      if(this.role == null){
         this.role = new ArrayList<>();
         this.role.add(role);
      }
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getPassword() {
      return password;
   }

   @Override
   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();

      list.add(new SimpleGrantedAuthority("ROLE_" + role));

      return list;
   }


   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }


   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      User user = (User) o;
      return id.equals(user.id) && username.equals(user.username) && lastName.equals(user.lastName) && email.equals(user.email);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, username, lastName, email);
   }



}
