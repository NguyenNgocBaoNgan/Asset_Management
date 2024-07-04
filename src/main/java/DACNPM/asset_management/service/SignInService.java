package DACNPM.asset_management.service;

import DACNPM.asset_management.model.Account;
import DACNPM.asset_management.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignInService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Account checkLogin(int id, String rawPassword) {
        Account account = accountRepository.checkLogin(id);

        if (account != null) {
            // Kiểm tra nếu mật khẩu đã được mã hóa
            if (passwordEncoder.matches(rawPassword, account.getPassword())) {
                return account;
            }
            // Kiểm tra nếu mật khẩu chưa được mã hóa
            if (account.getPassword().equals(rawPassword)) {
                return account;
            }
        }
        return null;
    }
}
