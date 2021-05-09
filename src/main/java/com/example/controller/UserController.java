package com.example.controller;

import com.example.config.JwtTokenUtil;
import com.example.model.Expert;
import com.example.model.User;
import com.example.repository.userRepository;
import com.example.service.userService.userService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*",  allowedHeaders = "*")
@RestController
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
        if (user != null && userRepository.findUserByEmail(user.getEmail()) == null) {
            User newUser = userService.createNewUser(user);
            return ResponseEntity.ok().body(user);

        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok(JwtTokenUtil.generateJwtToken(authentication));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping("/users/mail/{mail}")
    public ResponseEntity<User>  findUserByEmail(@PathVariable("email") String email) {
        log.info("REST request to find one user by email:[]", email);
        User userOpt = userService.findUserByEmail(email);
        if( userOpt != null){
            return ResponseEntity.ok().body(userOpt);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }}
}