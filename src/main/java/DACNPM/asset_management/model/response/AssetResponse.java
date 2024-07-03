package DACNPM.asset_management.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssetResponse {
    private Integer idAsset;
    private String assetName;
    private String description;
    private String purchasePrice;
    private LocalDate datePurchase;
}
