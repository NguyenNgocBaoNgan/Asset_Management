package DACNPM.asset_management.repository;

import DACNPM.asset_management.model.BorrowId;
import DACNPM.asset_management.model.ListBorrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ListBorrowRepository extends JpaRepository<ListBorrow, BorrowId> {
<<<<<<< Updated upstream:Asset_management/src/main/java/DACNPM/asset_management/repository/ListBorrowRepository.java
=======
    @Query(value = "select * from list_borrow where id_account = :idAccount and id_asset = :idAsset",nativeQuery = true)
    ListBorrow findByIdAccountAndIdAsset(@Param("idAccount") int idAccount, @Param("idAsset") int idAsset);


//    @Query(value = "SELECT asset.asset_name FROM asset join list_borrow WHERE asset.id_asset = list_borrow.id_asset",nativeQuery = true)
//    String callName(@Param("id_asset") Integer id_asset,@Param("id") int id);

>>>>>>> Stashed changes:src/main/java/DACNPM/asset_management/repository/ListBorrowRepository.java

}
