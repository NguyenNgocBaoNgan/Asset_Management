package DACNPM.asset_management.model.dto;

import jakarta.annotation.Nullable;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ListBorrowDTO {
    @Nullable
    private Integer idAccount;
    private Integer idAsset;
    private Integer quantity;
}
