package service;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.util.Properties;

public class EmailSender {

    public static void sendEmail(String recipientEmail, String subject, String content) {
        // Informations sur le serveur SMTP
        String host = "smtp.gmail.com"; // exemple : Gmail SMTP
        String port = "587";
        final String username = "diakitetenin99@gmail.com"; // Remplace par ton adresse Gmail
        final String password = "bzfd sjle fbgu bhuh"; // Mot de passe d'application (PAS le vrai mot de passe Gmail)

        // Configuration des propriétés du serveur SMTP
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true"); // Activer l'authentification
        props.put("mail.smtp.starttls.enable", "true"); // Activer STARTTLS
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        // Création d'une session authentifiée
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Création de l'objet Message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username)); // Expéditeur
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail)); // Destinataire
            message.setSubject(subject); // Sujet
            message.setText(content); // Corps du message (texte simple)

            // Envoi du message
            Transport.send(message);

            System.out.println("Email envoyé avec succès à " + recipientEmail);

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'envoi de l'email : " + e.getMessage());
        }
    }

    // Test de la méthode d'envoi

}
