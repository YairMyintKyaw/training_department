package com.trainingprogram.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.trainingprogram.model.CourseRegister;

public class EmailSendUtils {

	public void SendEmail(CourseRegister userInfo) {

		String coureName = userInfo.getCourseName();
		String schduleTime = userInfo.getTime();

		final String username = "yemyintkyaw241220@gmail.com";
		final String password = "apnnuswzdcbwvrup"; // app code copy and past here

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true"); // TLS
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("yemyintkyaw241220@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userInfo.getEmailId()));
			message.setSubject("Information About the Registered Session ");
			message.setText("Hello," + userInfo.getUserName()
					+ "\n This is the Training Department. Thank You for Registering to Attend Course."
					+ "\n Your Select Course : " + coureName
					+ "\n Your Schedule : " + schduleTime
					+ "\n Fee :" + userInfo.getPrice()
					+ "\n Looking forwards to seeing you in the classroom.");

			Transport.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();

		}

	}

}

//How to Set up 2 Step Verification in Gmail
//https://www.youtube.com/watch?v=H-DiXjk8-0w

//How to Generate Gmail App Password | Secure Google Account

//https://www.youtube.com/watch?v=hXiPshHn9Pw
