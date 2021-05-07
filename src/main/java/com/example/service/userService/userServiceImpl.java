package com.example.service.userService;
import com.example.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;



import com.example.model.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class userServiceImpl implements userService {

    @Autowired
    userRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User createNewUser(User user) {
        if(repository.existsUserByEmail(user.getEmail())){
            return null;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public Boolean findByEmailAndPassword(String email, String password) {
        if(repository.existsUserByEmail(email)){
            User user=repository.findUserByEmail(email);
            return passwordEncoder.matches(password,user.getPassword());
        }
        return false;
    }
    }

