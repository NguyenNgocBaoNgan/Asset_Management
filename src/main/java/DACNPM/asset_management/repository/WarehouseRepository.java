package DACNPM.asset_management.repository;

import DACNPM.asset_management.model.Account;
import DACNPM.asset_management.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse,Integer> {
    @Query(value = "CALL check_quantity(:id_asset);", nativeQuery = true)
    List<Integer> checkQuantity(@Param("id_asset") int id_asset);
}
