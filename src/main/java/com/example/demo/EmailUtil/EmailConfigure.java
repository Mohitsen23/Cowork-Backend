package com.example.demo.EmailUtil;
import java.util.Random;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
@Component
public class EmailConfigure {
		
@Autowired
private JavaMailSender mailSender;

private static final int OTP_LENGTH = 6; // Length of the OTP

public static String generateOTP() {
        Random random = new Random();
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(random.nextInt(10));
        }
         
       
        return otp.toString();
    }

public String otp;
public String sendEmailWithAttachMent(String email) {
	
	MimeMessage mimeMessage=mailSender.createMimeMessage();
	try {
		
		 otp=generateOTP();
		System.out.println("OTP Generation"+otp);
		MimeMessageHelper mimeMessageHelper =new MimeMessageHelper(mimeMessage, true);
		String subject="Confirm Your Email";
		String body="This is the OTP below for Your Email Confirmation :-  "+otp;
		
		mimeMessageHelper.setFrom("mohitsen623@gmail.com");
		mimeMessageHelper.setTo(email);
		
		mimeMessageHelper.setText(body);
		mimeMessageHelper.setSubject(subject);
        mailSender.send(mimeMessage);
		return "Mail Sent SuucessFull";
		
	} catch (MessagingException e) {
		
		e.printStackTrace();
	}
	return null;
	
}
	
}