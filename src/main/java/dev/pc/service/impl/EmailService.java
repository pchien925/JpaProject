package dev.pc.service.impl;

import java.util.Properties;

import dev.pc.configs.EmailConfig;
import dev.pc.dto.request.RegisterRequest;
import dev.pc.entity.User;
import dev.pc.service.IEmailService;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailService implements IEmailService {

	@Override
	public boolean comfirmRegistered(RegisterRequest request) {
		boolean test = false;
		String toMail = request.getEmail();
		String fromMail = "phamchien9254@gmail.com";
		String password = "oqvc caqw eboj zbwg";

		try {
			Properties pr = EmailConfig.configMail();

			Session session = Session.getInstance(pr, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromMail, password);
				}
			});

			Message mess = new MimeMessage(session);
			mess.setHeader("Content-Type", "text/plain; charset=UTF-8");

			mess.setFrom(new InternetAddress(fromMail));

			mess.addRecipient(Message.RecipientType.TO, new InternetAddress(toMail));
			mess.setSubject("Comfirm Code");

			mess.setText("Code to active account is " + request.getCode());

			Transport.send(mess);

			test = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return test;
	}

	@Override
	public boolean getPassword(User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
