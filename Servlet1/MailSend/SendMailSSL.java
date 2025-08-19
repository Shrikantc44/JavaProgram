import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendMailSSL {
    public static void main(String[] args) {
        // Email receiver ka address - yahan apna email likho
        String to = "shrikantchauhan5486@gmail.com"; 

        // Session ke liye SMTP settings
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        try {
            // Session object banao (MyAuth custom authenticator ke sath)
            Session session = Session.getDefaultInstance(props, new MyAuth());

            // Mail compose karo
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("skrajchauhan534@gmail.com")); // Sender ka email (correct kiya)
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to)); // Receiver ka email

            message.setSubject("First Mail"); // Subject set karo

            // Mail ke body parts banao
            Multipart body = new MimeMultipart();

            // First part: Text body
            MimeBodyPart part1 = new MimeBodyPart();
            part1.setText("this is body...");
            body.addBodyPart(part1);

            // Second part: Attachment
            MimeBodyPart part2 = new MimeBodyPart();
            // File location mein double backslash lagao
            FileDataSource fds = new FileDataSource("D:\\Demo\\Servlet1\\MailSend\\cbse.jpg");
            part2.setDataHandler(new DataHandler(fds));
            part2.setFileName(fds.getName());
            body.addBodyPart(part2);

            // Poora body message mein set karo
            message.setContent(body);

            // Email bhejo
            Transport.send(message);

            System.out.println("message sent successfully");
        } catch (Exception e) {
            System.out.println(e + "sss");
        }
    }
}

// Email authentication class
class MyAuth extends javax.mail.Authenticator {
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("skrajchauhan534@gmail.com", "pmux ytwj xgfx myvt");
        // Yeh password app password hai, original email password nahi
    }
}
