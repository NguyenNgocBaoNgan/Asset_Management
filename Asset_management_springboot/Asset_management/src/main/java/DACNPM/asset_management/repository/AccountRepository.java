package DACNPM.asset_management.repository;

import DACNPM.asset_management.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Integer> {
}
