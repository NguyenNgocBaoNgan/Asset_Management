package DACNPM.asset_management.repository;

import DACNPM.asset_management.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query(value = "CALL checkLogin(:id_account);", nativeQuery = true)
    Account checkLogin(@Param("id_account") int id_account);



}
