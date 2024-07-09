package DACNPM.asset_management.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {
    private Integer idAccount;
    private String firstName;
    private String lastName;
    private Date dayOfBirth;
    private String mail;
    private int role;

    public  String getFullName(){
        return this.getFirstName() + " " + this.getLastName();
    }
}
