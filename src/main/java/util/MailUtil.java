package util;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class MailUtil {

    private static final String EMAIL_REMITENTE = "tomasdiaz04032@gmail.com";
    private static final String PASSWORD = "alir fuoc bvrp bruy";

    public static void enviarMail(String destinatario, String nombre) throws MessagingException {

        // CONFIGURACION DEL SERVIDOR SMTP DE GMAIL
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        // AUTENTICACION CON LA CUENTA DE GMAIL
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_REMITENTE, PASSWORD);
            }
        });

        // ARMAMOS EL MAIL
        Message mensaje = new MimeMessage(session);
        mensaje.setFrom(new InternetAddress(EMAIL_REMITENTE));
        mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
        mensaje.setSubject("¡Bienvenido a la pagina web!");
        mensaje.setText("Hola " + nombre + ",\n\n" +
                "Tu cuenta fue creada con éxito. Ya podés iniciar sesión.\n\n" +
                "Saludos,\nTomas y Belen.");

        // ENVIAMOS EL MAIL
        Transport.send(mensaje);
    }
}
