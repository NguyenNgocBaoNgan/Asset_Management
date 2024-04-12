package DACNPM.asset_management.model;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class BorrowId implements Serializable {

    @Column(name = "id_account")
    private Integer idAccount;

    @Column(name = "id_asset")
    private Integer idAsset;

}