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
    private Integer idAsset;

    @Column(name="asset_name")
    private String assetName;

    @Column(name="description")
    private String description;

    @Column(name="id_status")
    private int status;

    @Column(name="purchase_price")
    private Integer purchasePrice;



    @Column(name="date_purchase")
    private LocalDate datePurchase;

    @Column(name="id_type")
    private int type;

}
