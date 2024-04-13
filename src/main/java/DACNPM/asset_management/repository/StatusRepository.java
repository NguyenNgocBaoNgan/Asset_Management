package DACNPM.asset_management.repository;

import DACNPM.asset_management.model.Status;
import DACNPM.asset_management.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status,Integer> {
    @Query(value = "select s.id,s.value from  status s join asset a on s.id=a.id_status where a.id_asset=:idAsset",nativeQuery = true)
    Status findStatusByIdAsset(@Param("idAsset") int idAsset);
}
