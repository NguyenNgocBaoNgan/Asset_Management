package DACNPM.asset_management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="asset")
public class Asset {
    @Id
    @Column(name="id_asset")
    private int idAsset;

    @Column(name="asset_name")
    private String assetName;

    @Column(name="description")
    private String description;

    @Column(name="id_status")
    private Integer status;

    @Column(name="purchase_price")
    private int purchasePrice;



    @Column(name="date_purchase")
    private LocalDate datePurchase;

    @Column(name="id_type")
    private int type;

}
