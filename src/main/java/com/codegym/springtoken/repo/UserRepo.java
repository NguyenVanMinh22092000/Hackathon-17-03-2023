package com.codegym.springtoken.repo;

import com.codegym.springtoken.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String name);
}
