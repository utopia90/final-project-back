package com.example.service.userService;
import com.example.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;



import com.example.model.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.ArrayList;

import static java.util.Collections.emptyList;

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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User applicationUser = repository.findUserByEmail(email);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(email);
        }
        return new org.springframework.security.core.userdetails.User(applicationUser.getEmail(), applicationUser.getPassword(),
                new ArrayList<>());
    }

}


