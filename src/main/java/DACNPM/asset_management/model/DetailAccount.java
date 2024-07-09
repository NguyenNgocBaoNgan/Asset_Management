package DACNPM.asset_management.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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
