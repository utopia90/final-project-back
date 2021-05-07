package com.example.controller;

import com.example.config.JwtTokenUtil;
import com.example.model.User;
import com.example.repository.userRepository;
import com.example.service.userService.userService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class UserController {

    private final userService userService;
    private final Logger log = LoggerFactory.getLogger(UserController.class);

    public UserController(userService userService) {
        this.userService = userService;
    }

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    userRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtTokenUtil JwtTokenUtil;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        if (user != null) {
            User newUser = userService.createNewUser(user);
        }
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<User> userLogin(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = JwtTokenUtil.generateJwtToken(authentication);
            User userBean = (User) authentication.getPrincipal();

        return ResponseEntity.ok(user);

    }
}