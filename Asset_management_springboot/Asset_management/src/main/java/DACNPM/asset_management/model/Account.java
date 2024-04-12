package DACNPM.asset_management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="acount")
public class Account {

    @Id
    @Column(name="id_account")
    private int idAccount;

    @Column(name="password")
    private String password;

    @Column(name="role")
    private int role;


}
