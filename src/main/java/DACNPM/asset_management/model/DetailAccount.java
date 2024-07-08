package DACNPM.asset_management.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="detail_account")
public class DetailAccount {

    @Id
    @Column(name="id_account")
    private Integer idAccount;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="day_of_birth")
    private Date dayOfBirth;

    @Column(name="mail")
    private String mail;

    @Column(name="role")
    private int role;

    @OneToOne
    @JoinColumn(name = "id_account", referencedColumnName = "id_account")
    private Account account;
}
