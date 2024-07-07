package DACNPM.asset_management.repository;

import DACNPM.asset_management.model.Comment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query("SELECT c FROM Comment c WHERE c.assetId=:assetId")
    List<Comment> findCommentByAssetId(@Param("assetId") int assetId);

    @Query(value = "SELECT CONCAT(d.first_name,' ',d.last_name) FROM detail_account d JOIN comment c " +
            "ON d.id_account=c.account_id WHERE c.id=:id",nativeQuery = true)
    String getFullNameAccountById(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO comment (account_id,asset_id,content,created_at) " +
                    "VALUES(:accountId,:assetId,:content,:createdAt)",nativeQuery = true)
    void addComment(@Param("accountId") int accountId,@Param("assetId") int assetId,
                    @Param("content") String content,@Param("createdAt") Date createdAt);
}
