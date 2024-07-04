package DACNPM.asset_management.service;

import DACNPM.asset_management.model.Account;
import DACNPM.asset_management.model.DetailAccount;
import DACNPM.asset_management.repository.AccountRepository;
import DACNPM.asset_management.repository.DetailAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Service
public class SignUpService {

    @Autowired
    private DetailAccountRepository detailAccountRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private EmailService emailService;

    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String OTHER_CHAR = "!@#$%&*()_+-=[]|,./?><";

    private static final String PASSWORD_ALLOW_BASE = CHAR_LOWER + CHAR_UPPER + NUMBER + OTHER_CHAR;

    private static SecureRandom random = new SecureRandom();
    private static final String DATE_PATTERN = "^\\d{4}/\\d{2}/\\d{2}$";

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void register(DetailAccount da, Account acc) throws MessagingException {
        int userId = generateUserId();
        da.setIdAccount(userId);
        acc.setId_account(userId);
        String userPass = generateRandomPassword(8);

        // Mã hóa mật khẩu
        String encodedPassword = passwordEncoder.encode(userPass);
        acc.setPassword(encodedPassword);
        acc.setRole(da.getRole());

        // Lưu thông tin vào database
        accountRepository.save(acc);
        detailAccountRepository.save(da);

        // Gửi email cho người dùng với mật khẩu chưa mã hóa sau khi lưu vào database
        String subject = "Your New Account Password";
        String content = "Dear user,\n\n" +
                "Your new account has been created.\n" +
                "Your ID and password is: " +da.getIdAccount() + " and "+  userPass + "\n\n" +
                "Please change your password after logging in.\n\n" +
                "Best regards,\n" +
                "Never Give Up Team";

        emailService.sendEmail(da.getMail(), subject, content);
    }

    private int generateUserId() {
        Random random = new Random();
        int min = 100000; // Giá trị nhỏ nhất của id, 6 chữ số
        int max = 999999; // Giá trị lớn nhất của id, 6 chữ số
        return random.nextInt(max - min + 1) + min;
    }

    private String generateRandomPassword(int length) {
        if (length < 4) {
            throw new IllegalArgumentException("Password length must be at least 4 characters");
        }

        StringBuilder password = new StringBuilder(length);
        String allowBase = PASSWORD_ALLOW_BASE;

        if (length > 8) {
            allowBase += NUMBER;
        }

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(allowBase.length());
            password.append(allowBase.charAt(randomIndex));
        }

        return password.toString();
    }

    public boolean isValidDateFormat(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        sdf.setLenient(false); // Đặt giá trị lenient thành false để kiểm tra chặt chẽ định dạng ngày tháng
        try {
            // Chuyển đổi đối tượng Date thành chuỗi ngày tháng theo định dạng yyyy/MM/dd
            String dateString = sdf.format(date);
            // Parse lại chuỗi ngày tháng thành đối tượng Date để kiểm tra xem có trùng khớp không
            Date parsedDate = sdf.parse(dateString);
            // Nếu hai đối tượng Date giống nhau, tức là ngày tháng đúng định dạng
            return date.equals(parsedDate);
        } catch (Exception e) {
            // Nếu có bất kỳ ngoại lệ nào xảy ra, tức là ngày tháng không đúng định dạng
            System.out.println("aaaaaaaaaaa");
            return false;
        }
    }
}
