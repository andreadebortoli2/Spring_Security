package com.dba.Spring_Security.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dba.Spring_Security.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
