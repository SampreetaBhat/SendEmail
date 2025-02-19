package com.EmailSender.SendEmail.Controller;

import com.EmailSender.SendEmail.Model.Email;
import com.EmailSender.SendEmail.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/V1")
public class EmailServiceController {
    @Autowired
    EmailService emailService;

    @PostMapping("/email")
    public String sendEmail(@RequestBody Email email){
        return emailService.sendEmail(email);
    }



}
