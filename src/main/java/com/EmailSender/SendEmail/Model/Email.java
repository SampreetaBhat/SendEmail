package com.EmailSender.SendEmail.Model;

import java.util.List;

public class Email {
    private List<String> recipients;
    private String msgBody;
    private String subject;
    
    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public String getMsgBody() {
        return msgBody;
    }



    public String getSubject() {
        return subject;
    }





}
