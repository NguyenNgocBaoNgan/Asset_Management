package DACNPM.asset_management.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="warehouse")
public class Warehouse {
    @Id
    @Column(name="id_asset")
    private int idAsset;

    @Column(name="stock_quantity")
    private String stockQuantity;

    @Column(name="unavailable_quantity")
    private String unavailableQuantity;

}
