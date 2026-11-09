package za.ac.cput.itemhivesd2.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender mailSender;
    private final String from;
    private final boolean enabled;

    public EmailService(JavaMailSender mailSender,
                        @Value("${app.mail.from}") String from,
                        @Value("${app.mail.enabled:true}") boolean enabled) {
        this.mailSender = mailSender;
        this.from = from;
        this.enabled = enabled;
    }

    public void sendVerificationCode(String to, String code) {
        if (!enabled) {
            // DEVELOPMENT ONLY: prints the code in the backend terminal instead of emailing it.
            log.warn("EMAIL DISABLED. Verification code for {} is {}", to, code);
            return;
        }

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(from);
        msg.setTo(to);
        msg.setSubject("Your ItemHive verification code");
        msg.setText("Your ItemHive verification code is " + code
                + ".\nIt expires in 15 minutes.\n\nIf you didn't create an account, ignore this email.");
        mailSender.send(msg);
    }
}