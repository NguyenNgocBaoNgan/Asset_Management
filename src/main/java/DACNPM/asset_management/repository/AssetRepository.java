package DACNPM.asset_management.repository;

import DACNPM.asset_management.model.Asset;
import DACNPM.asset_management.model.Status;
import DACNPM.asset_management.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssetRepository extends JpaRepository<Asset,Integer> {

    @Query("SELECT a FROM Asset a WHERE CONCAT(a.idAsset,a.assetName,a.description,a.status,a.purchasePrice,a.datePurchase,a.type) LIKE %?1%")
    public List<Asset> search(String keyword);



    @Query("SELECT s FROM Status s JOIN Asset a ON s.idStatus=a.status WHERE a.idAsset=:id")
    Optional<Status> findStatusByAssetId(@Param("id") int id);

    @Query("SELECT p FROM Type p JOIN Asset a ON p.typeId=a.type WHERE a.idAsset=:id")
    Optional<Type> findTypeByAssetId(@Param("id") int id);
}
