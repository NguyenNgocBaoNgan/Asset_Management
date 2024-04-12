package DACNPM.asset_management.repository;

import DACNPM.asset_management.model.BorrowId;
import DACNPM.asset_management.model.ListBorrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ListBorrowRepository extends JpaRepository<ListBorrow, BorrowId> {
    @Query(value = "select * from list_borrow where id_account = :idAccount and id_asset = :idAsset",nativeQuery = true)
    ListBorrow findByIdAccountAndIdAsset(@Param("idAccount") int idAccount, @Param("idAsset") int idAsset);
}
