package org.example;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.time.LocalDate;
import java.util.Properties;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.MessagingException;

public class NotificationEmail {
        public static void envoyer(Message message, Employe destinataire) {
            final String username = "diakitetenin99@gmail.com";
            final String password = "bzfd sjle fbgu bhuh ";

            Properties prop = new Properties();
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getInstance(prop, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                jakarta.mail.Message msg = new MimeMessage(session); // <- on précise ici
                msg.setFrom(new InternetAddress(username));
                msg.setRecipients(jakarta.mail.Message.RecipientType.TO, InternetAddress.parse(destinataire.getEmail()));
                msg.setSubject("Notification");
                msg.setText(message.getContenu());

                Transport.send(msg);

                System.out.println("Email envoyé à " + destinataire.getEmail());
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }

    public static void main(String[] args) {
        Message msg = new Message(1,"Bonjour Hamza", LocalDate.now());
        Employe emp = new Employe(1,"Sanmo","Hamza", Poste.DEVELOPPEUR,"hamzasanmo@gmail.com");

        envoyer(msg,emp);
    }
}
