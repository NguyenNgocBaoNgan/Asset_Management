package DACNPM.asset_management.repository;

import DACNPM.asset_management.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TypeRepository extends JpaRepository<Type,Integer> {

    @Query(value = "select t.id_type,t.value,t.description from  type t join asset a on t.id_type=a.id_type where a.id_asset=:idAsset",nativeQuery = true)
    Type findTypeByIdAsset(@Param("idAsset") int idAsset);
}
