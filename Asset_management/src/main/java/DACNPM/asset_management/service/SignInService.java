package DACNPM.asset_management.service;

import DACNPM.asset_management.model.Account;
import DACNPM.asset_management.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignInService {

    @Autowired
    AccountRepository accountRepository;

    public Account checkLogin(int id, String pass) {
        return accountRepository.checkLogin(id, pass);
    }

}
