package com.example.service.userService;

import com.example.model.User;

public interface userService {

    User createNewUser(User user);
    Boolean findByEmailAndPassword(String email , String password);


}
