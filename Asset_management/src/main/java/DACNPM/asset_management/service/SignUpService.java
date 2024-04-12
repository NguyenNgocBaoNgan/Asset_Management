package DACNPM.asset_management.service;
import DACNPM.asset_management.model.Account;
import DACNPM.asset_management.model.DetailAccount;
import DACNPM.asset_management.repository.AccountRepository;
import DACNPM.asset_management.repository.DetailAccountRepository;
import jakarta.annotation.Resource;
import DACNPM.asset_management.model.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.stereotype.Service;
import DACNPM.asset_management.repository.DetailAccountRepository;

import java.security.SecureRandom;
import java.util.Random;

@Service
public class SignUpService {
    @Autowired
    private DetailAccountRepository detailAccountRepository;
    @Autowired
    private AccountRepository accountRepository;

    }

