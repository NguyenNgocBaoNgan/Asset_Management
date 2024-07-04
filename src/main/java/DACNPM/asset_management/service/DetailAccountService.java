package DACNPM.asset_management.service;

import DACNPM.asset_management.model.DetailAccount;
import DACNPM.asset_management.repository.DetailAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailAccountService {
    @Autowired
    DetailAccountRepository detailAccountRepository;
    public List<DetailAccount> getAllUser(String keyword){
        if (keyword != null) {
            return detailAccountRepository.search(keyword);
        }
        return detailAccountRepository.findAll();
    }


    public void updateUser(int id,DetailAccount updatedDetailAccount) throws Exception {
        Optional<DetailAccount> oldUser= detailAccountRepository.findById(id);
        if (oldUser.isPresent()) {
            DetailAccount detailAccount = oldUser.get();
            // Cập nhật thông tin của tài sản với dữ liệu từ updatedAsset
            detailAccount.setFirstName(updatedDetailAccount.getFirstName());
            detailAccount.setLastName(updatedDetailAccount.getLastName());
            detailAccount.setDayOfBirth(updatedDetailAccount.getDayOfBirth());
            detailAccount.setMail(updatedDetailAccount.getMail());
            detailAccount.setRole(updatedDetailAccount.getRole());

            // Lưu thay đổi vào cơ sở dữ liệu
            detailAccountRepository.save(detailAccount);
        } else {
            // Xử lý trường hợp không tìm thấy tài sản với ID đã cung cấp
            throw new Exception("Asset with id " + id + " not found");
        }
    }

    public void deleteUser(int id){
        detailAccountRepository.deleteById(id);
    }
}
