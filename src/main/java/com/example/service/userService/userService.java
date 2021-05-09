package com.example.service.userService;

import com.example.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface userService extends UserDetailsService {

    User createNewUser(User user);
    Boolean findByEmailAndPassword(String email , String password);


    User findUserByEmail(String email);
}
