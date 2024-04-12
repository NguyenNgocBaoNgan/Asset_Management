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

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="list_borrow")
public class ListBorrow {
    @Id
    @Column(name="id_account")
    private int idAccount;

    @Column(name="id_asset")
    private String idAsset;

    @Column(name="borrow_date")
    private Date borrowDate;

    @Column(name="returnDate")
    private Date returnDate;

    @Column(name="quantity")
    private int quantity;
    @Column(name = "status")
    private int status;
}
