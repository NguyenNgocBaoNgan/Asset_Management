package DACNPM.asset_management.model.dto;

import jakarta.annotation.Nullable;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    @Nullable
    private int id;
    @Nullable
    private int accountId;
    @NonNull
    private int assetId;
    private String content;
}
