package DACNPM.asset_management.model.dto;

import jakarta.annotation.Nonnull;
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
    @Nonnull
    private Integer idAsset;
    @Nonnull
    private Integer quantity;
}
