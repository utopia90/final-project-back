package com.example.controller;

import com.example.model.Email;
import com.example.service.emailService.EmailService;
import com.example.service.userService.userService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class MailController {


    private final EmailService emailService;
    private final Logger log = LoggerFactory.getLogger(MailController.class);

    public MailController(EmailService emailService) {
        this.emailService = emailService;
    }
    @PostMapping("/registration-email")
    public ResponseEntity<Email> sendEmail(@RequestBody Email email){

            emailService.sendEmail(email);
            return new ResponseEntity<>(email,  HttpStatus.OK);
    }
}
