package DACNPM.asset_management.repository;

import DACNPM.asset_management.model.BorrowId;
import DACNPM.asset_management.model.ListBorrow;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListBorrowRepository extends JpaRepository<ListBorrow, BorrowId> {
    @Query(value = "select * from list_borrow where id_account = :idAccount and id_asset = :idAsset and status = 0", nativeQuery = true)
    ListBorrow findByIdAccountAndIdAsset(@Param("idAccount") int idAccount, @Param("idAsset") int idAsset);

    @Query(value = "select asset_name from asset where id_asset = :idAsset", nativeQuery = true)
    String nameAsset(@Param("idAsset") int idAsset);

    // list nhung yeu cau muon tai san cua user
    @Query(value = "select * from list_borrow where id_account = :idAccount", nativeQuery = true)
    List<ListBorrow> listAllRequestUser(@Param("idAccount") int idAccount);

    // list nhung yeu cau muon tai san
    @Query(value = "select * from list_borrow", nativeQuery = true)
    List<ListBorrow> listAllRequest();

    // list nhung yeu cau da cho muon
    @Query(value = "select * from list_borrow where status = 1", nativeQuery = true)
    List<ListBorrow> listAllRequestSubmit();

    // list nhung yeu cau da tra lai tai san
    @Query(value = "select * from list_borrow where status = 2", nativeQuery = true)
    List<ListBorrow> listAllResponse();
    @Transactional
    @Modifying
    @Query(value = "update list_borrow set status = 1,return_date = CURRENT_TIMESTAMP  where id_account = :idAccount and id_asset = :idAsset and status = 0", nativeQuery = true)
    int updateStatus(@Param("idAccount") int idAccount, @Param("idAsset") int idAsset);

}