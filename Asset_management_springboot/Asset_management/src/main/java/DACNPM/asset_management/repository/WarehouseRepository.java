package DACNPM.asset_management.repository;

import DACNPM.asset_management.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse,Integer> {
}
