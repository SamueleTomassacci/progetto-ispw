package it.uniroma2.dicii.ispw.utils.engineering;

import it.uniroma2.dicii.ispw.utils.bean.EmailBean;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailEngineering {


    public void mandaEmail(EmailBean email) {

        String host = "smtp.libero.it";
        String username = "progettoispw@libero.it";
        String password = "b@Js9C-uy_w49BW";

        // Proprietà per configurare la connessione al server SMTP
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Creazione della sessione
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(username, password);
            }
        });

        try {
            // Creazione del messaggio
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("progettoispw@libero.it"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getEmail()));
            message.setSubject(email.getSubject());
            message.setText(email.getText());

            // Invio del messaggio
            Transport.send(message);


        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Errore nell'invio dell'email: " + e.getMessage());
        }
    }


}

