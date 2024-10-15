package dev.pc.configs;

import java.util.Properties;

public class EmailConfig {
	public static Properties configMail() {
		Properties pr = new Properties();
		pr.setProperty("mail.smtp.host", "smtp.gmail.com");
		pr.setProperty("mail.smtp.port", "587");
		pr.setProperty("mail.smtp.auth", "true");
		pr.setProperty("mail.smtp.starttls.enable", "true");
		pr.put("mail.smtp.soketFactory.port", "587");
		pr.put("mail.smtp.soketFactory.class", "javax.net.ssl.SSLSocketFactory");

		return pr;
	}
}
