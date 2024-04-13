package DACNPM.asset_management.repository;

import DACNPM.asset_management.model.DetailAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailAccountRepository extends JpaRepository<DetailAccount,Integer> {

}
