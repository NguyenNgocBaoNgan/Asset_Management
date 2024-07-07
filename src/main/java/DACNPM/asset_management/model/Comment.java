package DACNPM.asset_management.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="comment")
public class Comment {

    @Id
    @Column(name="id")
    private int id;

    @Column(name="account_id")
    private int accountId;
    @Column(name="asset_id")
    private int assetId;

    @Column(name="content")
    private String content;

    @Column(name="created_at")
    private Date createdAt;

    public Comment(int accountId, int assetId, String content, Date createdAt) {
        this.accountId = accountId;
        this.assetId = assetId;
        this.content = content;
        this.createdAt = createdAt;
    }


}
