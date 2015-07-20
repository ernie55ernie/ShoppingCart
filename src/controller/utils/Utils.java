/**
 * 
 */
package controller.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.FacebookType;

/**
 * @author ernie
 *
 */
public class Utils {

	/**
	 * @param to
	 * @param from
	 * @param host
	 * @param subject
	 * @param content
	 */
	public static void sendEmail(String to, String subject, String content) {

		final String username = "ernie55ernie@gmail.com";
		final String password = "A17B67C32D26";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(content);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

	public static void postFBMessage(String message) {
		FacebookClient facebookClient = new DefaultFacebookClient(
				"CAAELpjaHZCkwBAJl9ZAprFztZAZAEH7M3ZBjZAmPBcLHmhUy4YSfAfI02c2HwrxmV2ksZBx5CHcCuwkldmJtRURKjDGSPn9CZBlWrMbahvmSwMEpZBaTKzMboXUOfPxvSmYlL6zLaMrtQKgWCLEyeY4i1BapCP6Dk4tRSteqPzwQZBgAhQentZBXsA7yZAUvw6mIFWe4oSSUh9WsjAZDZD",
				"1875d9b68ca2f462a2c5a943b5223b3e",
				Version.VERSION_2_3);
		FacebookType publishMessageResponse = facebookClient.publish("game2048extend/feed",
				FacebookType.class, Parameter.with("message", message));

		System.out.println("Published message ID: " + publishMessageResponse.getId());

	}
}
