package com.example.service.emailService;


import com.example.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{

    private JavaMailSender javaMailSender;

    @Autowired
    public void EmailService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(Email email) throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("boemiastore@gmail.com");
        mail.setFrom("boemiastore@gmail.com");
        mail.setSubject("Contacto: "+email.getEmailContact());
        mail.setText("Le confirmamos que el registro se ha realizado con éxito ");

        javaMailSender.send(mail);
    }
}
