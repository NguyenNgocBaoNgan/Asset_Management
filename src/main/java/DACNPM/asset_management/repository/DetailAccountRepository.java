package DACNPM.asset_management.repository;

import DACNPM.asset_management.model.DetailAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DetailAccountRepository extends JpaRepository<DetailAccount,Integer> {
    @Query("SELECT a FROM DetailAccount a WHERE CONCAT(a.idAccount,a.firstName,a.lastName,a.dayOfBirth,a.mail,a.role) LIKE %?1%")
    public List<DetailAccount> search(String keyword);
    DetailAccount findByMail(String mail);
}
