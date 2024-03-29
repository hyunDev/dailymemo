package org.hyunpro.webapp.dailymemo.Account;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailServiceImpl {
    JavaMailSender emailSender;

    public void setJavaMailSender(JavaMailSender javaMailSender){
        this.emailSender=javaMailSender;
    }
    public boolean sendSimpleMessage(String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);//보낼 대상
        message.setSubject(subject);//제목
        message.setText(text);//내용
        try{//예외처리
            emailSender.send(message);
            return true;
        }catch(MailException es){
            es.printStackTrace();
            return false;
        }
    }
}
