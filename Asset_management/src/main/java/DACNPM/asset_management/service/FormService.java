package DACNPM.asset_management.service;

import DACNPM.asset_management.model.ListBorrow;
import DACNPM.asset_management.repository.AccountRepository;
import DACNPM.asset_management.repository.ListBorrowRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class FormService {
    @Resource
    ListBorrowRepository listBorrowRepository;
    @Resource
    AccountRepository accountRepository;
    ListBorrow createListBorrow;


}
