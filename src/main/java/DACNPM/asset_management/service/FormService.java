package DACNPM.asset_management.service;

import DACNPM.asset_management.mapper.MapperGenerate;
import DACNPM.asset_management.model.*;
import DACNPM.asset_management.model.dto.ListBorrowDTO;
import DACNPM.asset_management.model.response.ListBorrowResponse;
import DACNPM.asset_management.repository.AccountRepository;
import DACNPM.asset_management.repository.DetailAccountRepository;
import DACNPM.asset_management.repository.ListBorrowRepository;
import DACNPM.asset_management.repository.WarehouseRepository;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.http.HttpRequest;
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
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private DetailAccountRepository detailAccountRepository;
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
        ListBorrow entity = listBorrowRepository.findById(new BorrowId(idAccount, idAsset, 0)).orElse(null);
        if (entity == null) {
            return false;
        }
        int listBorrow = listBorrowRepository.updateStatus(idAccount, idAsset);
        if (listBorrow == 0) {
            return false;
        } else {
            Warehouse warehouse = warehouseRepository.findById(idAsset).orElse(null); // lấy warehouse trong db với asset
            if (warehouse == null) {
                return false;
            } else {
                warehouse.setUnavailableQuantity(warehouse.getUnavailableQuantity() - entity.getQuantity());  // gán số lượng đã mượn bằng cách cộng số lượng đã mượn trong db vs số lượng vừa mượn
                warehouseRepository.save(warehouse);
            }
        }
        return true;
    }

    public boolean create(ListBorrowDTO listBorrowDTO) {
        HttpSession session = request.getSession(); // lấy session
        Account account = (Account) session.getAttribute("loggedInAccount"); // lấy session với giá trị loggedInAccount
        if (!accountRepository.existsById(account.getId_account())) { // kiểm tra id có tồn tại trong bảng account
            MESS = "ACCOUNT NOT EXISTS";
            return false;
        }
        listBorrowDTO.setIdAccount(account.getId_account()); // gán id vào dto

        ListBorrow listBorrow = mapperGenerate.convert(listBorrowDTO, ListBorrow.class); // covert dto - entity
        listBorrow.setBorrowDate(new Date()); // gán giá trị ngày mượn
        listBorrow.getId().setStatus(0); // gán trạng thái
        listBorrow.setStatus(new Status(0));

        DetailAccount detailAccount = detailAccountRepository.findById(account.getId_account()).orElse(null);
        if (detailAccount == null) { // kiểm tra id có tồn tại trong bảng detailAccount
            return false;
        }
        listBorrow.setDetailAccount(detailAccount); // gán detailAccount

        if (listBorrowRepository.existsById(listBorrow.getId())) { // kiểm tra đã mượn tài sản chưa
            System.out.println(warehouseRepository.checkQuantity(listBorrowDTO.getIdAsset()).get(0));
            if (warehouseRepository.checkQuantity(listBorrow.getId().getIdAsset()).get(0) < listBorrow.getQuantity()) { // kiểm tra số lượng tồn kho
                MESS = "NOT ENOUGH QUANTITY";
                return false;
            }
            //update số lượng nếu đã mượn
            listBorrow.setQuantity(listBorrow.getQuantity() + listBorrowRepository.findByIdAccountAndIdAsset(listBorrow.getId().getIdAccount(), listBorrow.getId().getIdAsset()).getQuantity());
        }
        // thêm vào db
        createListBorrow = listBorrowRepository.save(listBorrow);

        // hiển thị lỗi nếu lưu db không thành công
        if (createListBorrow == null) {
            MESS = "ERROR";
            return false;
        } else {
            Warehouse warehouse = warehouseRepository.findById(listBorrow.getId().getIdAsset()).orElse(null); // lấy warehouse trong db với asset
            if (warehouse == null) {
                return false;
            } else {
                warehouse.setUnavailableQuantity(warehouse.getUnavailableQuantity() + listBorrowDTO.getQuantity());  // gán số lượng đã mượn bằng cách cộng số lượng đã mượn trong db vs số lượng vừa mượn
                warehouseRepository.save(warehouse);
            }
        }
        return true;
    }
}