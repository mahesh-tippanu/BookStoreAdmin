package com.example.bookstore_backend.service;
import com.example.bookstore_backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import javax.mail.internet.MimeMessage;
@Service
public class EmailService {
    @Autowired
    JavaMailSender javaMailSender;
    public  void sendmail(String email) {
        final String emailToRecipient = email;
        final String emailSubject = "Successfully Email Send by Person Email Address";
        final String emailMessage = "Sir/Mam, This is Mail Form DataBase , Your Details successfully Store In Database ";
        javaMailSender.send(new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                mimeMessageHelper.setTo(emailToRecipient);
                mimeMessageHelper.setText(emailMessage, true);
                mimeMessageHelper.setSubject(emailSubject);
                System.out.println("mailsend");
            }
        });
    }
}
