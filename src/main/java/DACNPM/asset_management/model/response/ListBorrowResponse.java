package DACNPM.asset_management.model.response;

import DACNPM.asset_management.model.Status;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ListBorrowResponse {
    private AccountResponse detailAccount;
    private AssetResponse asset;
    private Status status;
    private Date borrowDate;
    private Date returnDate;
    private Integer quantity;


}
