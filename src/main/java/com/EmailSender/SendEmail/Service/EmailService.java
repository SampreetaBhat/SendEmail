package com.EmailSender.SendEmail.Service;

import com.EmailSender.SendEmail.Model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}") private String sender;

    private final List<String> recipients = new ArrayList<>();


    public String  setRecipients(List<String> newRecipients) {
        this.recipients.clear();
        this.recipients.addAll(newRecipients);
        return "Recipients updated successfully!";
    }




    @Scheduled(cron = "0 0 13 * * ?")
    public void scheduleEmail() {
        if (recipients.isEmpty()) {
            System.out.println("recipients not found ");
        }
        String result = readFile();
        System.out.println(result);
    }


    public String readFile() {
        Email email = new Email();
        try (BufferedReader br = new BufferedReader(new FileReader("/home/sampreeta/Downloads/sendmail.txt"))) {
            String subject = br.readLine();
            StringBuilder body = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                body.append(line).append("\n");
            }

            email.setRecipients(recipients);
            email.setSubject(subject);
            email.setMsgBody(body.toString());

            return sendEmail(email);
        } catch (IOException e) {
            return e.getMessage();
        }
    }


    public String sendEmail(Email email) {
        try {
            if (email.getRecipients().isEmpty()) {
                return "Error: No recipients provided!";
            }

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(email.getRecipients().toArray(new String[0]));
            mailMessage.setText(email.getMsgBody());
            mailMessage.setSubject(email.getSubject());

            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Error while Sending Mail";
        }
    }


}
