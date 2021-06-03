package com.example.demo.repo;

import java.util.List;

import com.example.demo.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByVerificationCode(String verificationCode);

    @Query("SELECT u FROM User u WHERE CONCAT(u.firstName,u.lastName,u.email) LIKE %?1% AND u.userId != ?2")
    List<User> search(String keyword,Long userId);
}
