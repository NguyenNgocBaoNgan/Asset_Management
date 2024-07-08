package DACNPM.asset_management.model.response;

import jakarta.annotation.Nullable;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class CommentResponse {
    private int id;
    private AccountResponse detailAccount;
    private int assetId;
    private boolean isGood;
    private String content;
    private Date createdAt;
}
