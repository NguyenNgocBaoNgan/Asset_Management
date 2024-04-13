package DACNPM.asset_management.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "list_borrow")
public class ListBorrow {
    @EmbeddedId
    private BorrowId id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "borrow_date")
    private Date borrowDate;

    @Column(name = "returnDate")
    private Date returnDate;

    @Column(name = "quantity")
    private Integer quantity;


    public ListBorrow(BorrowId id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;

    }

    public ListBorrow(BorrowId borrowId) {
        this.id = borrowId;
    }


    @PrePersist
    protected void onCreate() {
        borrowDate = new Date();
    }
}
