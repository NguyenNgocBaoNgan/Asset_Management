package DACNPM.asset_management.service;

import DACNPM.asset_management.mapper.MapperGenerate;
import DACNPM.asset_management.model.BorrowId;
import DACNPM.asset_management.model.ListBorrow;
import DACNPM.asset_management.model.response.ListBorrowResponse;
import DACNPM.asset_management.repository.AccountRepository;
import DACNPM.asset_management.repository.ListBorrowRepository;
import DACNPM.asset_management.repository.WarehouseRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FormService {
    @Resource
    ListBorrowRepository listBorrowRepository;
    @Resource
    WarehouseRepository warehouseRepository;
    @Resource
    AccountRepository accountRepository;
    ListBorrow createListBorrow;
    @Autowired
    MapperGenerate mapperGenerate;

    public static String MESS = "SUCCESSFULLY";

    public List<ListBorrowResponse> listAllRequestUser(int id_account) {
        List<ListBorrowResponse> rs = mapperGenerate.toListConvert(listBorrowRepository.listAllRequestUser(id_account), ListBorrowResponse.class);
        return rs;
    }

    public List<ListBorrowResponse> listAllRequest() {
        List<ListBorrowResponse> rs = mapperGenerate.toListConvert(listBorrowRepository.listAllRequest(), ListBorrowResponse.class);

        return rs;
    }

    public String nameAsset(int id) {
        return listBorrowRepository.nameAsset(id);
    }

    public List<ListBorrow> listAllRequestSubmit() {
        return listBorrowRepository.listAllResponse();
    }

    public List<ListBorrow> listAllResponse() {
        return listBorrowRepository.listAllResponse();
    }

    public boolean update(int idAccount, int idAsset) {
        int listBorrow = listBorrowRepository.updateStatus(idAccount, idAsset);
        if (listBorrow == 0) {
            return false;
        }
        return true;
    }

    public boolean create(ListBorrow listBorrow) {
        if (!accountRepository.existsById(listBorrow.getId().getIdAccount())) {
            MESS = "ACCOUNT NOT EXISTS";

            return false;
        }
        if (listBorrowRepository.existsById(listBorrow.getId())) {
            System.out.println(warehouseRepository.checkQuantity(listBorrow.getId().getIdAsset()).get(0));
            if (warehouseRepository.checkQuantity(listBorrow.getId().getIdAsset()).get(0) < listBorrow.getQuantity()) {
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