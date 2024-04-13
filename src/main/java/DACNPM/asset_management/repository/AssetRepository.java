package DACNPM.asset_management.repository;

import DACNPM.asset_management.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<Asset,Integer> {

    @Query("SELECT a FROM Asset a WHERE CONCAT(a.idAsset,a.assetName,a.description,a.status,a.purchasePrice,a.datePurchase,a.type) LIKE %?1%")
    public List<Asset> search(String keyword);

}
