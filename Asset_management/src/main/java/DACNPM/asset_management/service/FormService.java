package DACNPM.asset_management.service;

import DACNPM.asset_management.model.ListBorrow;
import DACNPM.asset_management.repository.AccountRepository;
import DACNPM.asset_management.repository.ListBorrowRepository;
import DACNPM.asset_management.repository.WarehouseRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class FormService {
    @Resource
    ListBorrowRepository listBorrowRepository;
    @Resource
    WarehouseRepository warehouseRepository;
    @Resource
    AccountRepository accountRepository;
    ListBorrow createListBorrow;

    public static String MESS = "SUCCESSFULLY";

    public boolean create(ListBorrow listBorrow) {
        if (!accountRepository.existsById(listBorrow.getId().getIdAccount())) {
            MESS = "ACCOUNT NOT EXISTS";

            return false;
        }
        if (listBorrowRepository.existsById(listBorrow.getId())) {
            System.out.println(warehouseRepository.checkQuantity(listBorrow.getId().getIdAsset()).get(0));
            if(warehouseRepository.checkQuantity(listBorrow.getId().getIdAsset()).get(0) < listBorrow.getQuantity()){
                MESS = "NOT ENOUGH QUANTITY";
                return false;
            }
            listBorrow.setQuantity(listBorrow.getQuantity() + listBorrowRepository.findByIdAccountAndIdAsset(listBorrow.getId().getIdAccount(), listBorrow.getId().getIdAsset()).getQuantity());
        }
        createListBorrow = listBorrowRepository.save(listBorrow);
        if (createListBorrow == null) {
            MESS = "ERROR";
            return false;
        }
        return true;
    }
}
