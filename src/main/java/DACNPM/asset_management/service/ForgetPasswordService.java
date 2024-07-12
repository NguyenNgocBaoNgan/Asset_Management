package DACNPM.asset_management.service;

import DACNPM.asset_management.model.DetailAccount;
import DACNPM.asset_management.repository.DetailAccountRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import java.security.SecureRandom;

@Service
public class ForgetPasswordService {

    @Autowired
    private DetailAccountRepository detailAccountRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailService emailService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private static SecureRandom random = new SecureRandom();
    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String OTHER_CHAR = "!@#$%&*()_+-=[]|,./?><";
    private static final String PASSWORD_ALLOW_BASE = CHAR_LOWER + CHAR_UPPER + NUMBER + OTHER_CHAR;

    public void processForgotPassword(String email) throws MessagingException {
        DetailAccount detailAccount = detailAccountRepository.findByMail(email);
        if (detailAccount == null) {
            throw new IllegalArgumentException("No account found with the provided email");
        }

        // Generate a new temporary password
        String tempPassword = generateRandomPassword(8);

        // Send email with the temporary password
        String subject = "Your Temporary Password";
        String content = "Dear user,\n\n" +
                "Your password reset request has been processed.\n" +
                "Your temporary password is: " + tempPassword + "\n\n" +
                "Best regards,\n" +
                "Never Give Up Team";

        emailService.sendEmail(detailAccount.getMail(), subject, content);
        //sendEmail(detailAccount.getMail(), tempPassword);

        // Encrypt and update the new password
        String encodedPassword = passwordEncoder.encode(tempPassword);
        detailAccount.getAccount().setPassword(encodedPassword);
        detailAccountRepository.save(detailAccount);
    }

    private String generateRandomPassword(int length) {
        if (length < 4) {
            throw new IllegalArgumentException("Password length must be at least 4 characters");
        }

        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(PASSWORD_ALLOW_BASE.length());
            password.append(PASSWORD_ALLOW_BASE.charAt(randomIndex));
        }

        return password.toString();
    }

//    private void sendEmail(String to, String password) throws MessagingException {
//        String subject = "Your Temporary Password";
//        String content = "Dear user,\n\n" +
//                "Your password reset request has been processed.\n" +
//                "Your temporary password is: " + password + "\n\n" +
//                "Best regards,\n" +
//                "Never Give Up Team";
//
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
//        helper.setTo(to);
//        helper.setSubject(subject);
//        helper.setText(content, true);
//
//        mailSender.send(message);
//    }
}