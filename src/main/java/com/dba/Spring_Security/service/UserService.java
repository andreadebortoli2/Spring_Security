package com.dba.Spring_Security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dba.Spring_Security.dao.UserRepo;
import com.dba.Spring_Security.model.User;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    public User savUser(User user) {
        return repo.save(user);
    }
}
