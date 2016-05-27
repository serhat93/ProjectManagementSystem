package mail;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;

public class MailReceiver implements Runnable {

	String host, storeType, user, password;

	public MailReceiver(String host, String storeType, String user, String password) {
		this.host = host;
		this.storeType = storeType;
		this.user = user;
		this.password = password;
	}

	/**
	 * Belirli aralıklarla maillerin kontrol edilmesi sağlanır.
	 */
	@Override
	public void run() {
		while (true) {
			check();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void check() {
		try {

			// create properties field
			Properties properties = new Properties();

			properties.put("mail.store.protocol", "pop3");
			properties.put("mail.pop3.host", host);
			properties.put("mail.pop3.port", "995");
			properties.put("mail.pop3.starttls.enable", "true");
			properties.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			Session emailSession = Session.getDefaultInstance(properties);

			// create the POP3 store object and connect with the pop server
			Store store = emailSession.getStore("pop3s");

			store.connect(host, user, password);

			// create the folder object and open it
			Folder emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);

			// retrieve the messages from the folder in an array and print it
			Message[] messages = emailFolder.getMessages();
			System.out.println("messages.length---" + messages.length);

			for (int i = 0, n = messages.length; i < n; i++) {
				Message message = messages[i];
				if (message.getContent() instanceof Multipart) {
					MailParser.parseMessage(message);

					sendReplyMail(message, user, password);
				}
			}

			// close the store and folder objects
			emailFolder.close(false);
			store.close();

		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gelen maillere cevap maili göndermek için kullanılır.
	 */
	public static void sendReplyMail(Message message, String user, String password) {
		try {
			MailSender mailSender = new MailSender();
			mailSender.setHeader("Bilgilendirme");
			mailSender.setContent("Mesajınız başarı ile alınmıştır.");
			mailSender.setReceiver(InternetAddress.toString(message.getFrom()));
			mailSender.send(user, password);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		String host = "pop.gmail.com";// change accordingly
		String mailStoreType = "pop3";
		String username = "sytms2016@gmail.com";// change accordingly
		String password = "syt12345";// change accordingly

		(new Thread(new MailReceiver(host, mailStoreType, username, password))).start();
	}

}
