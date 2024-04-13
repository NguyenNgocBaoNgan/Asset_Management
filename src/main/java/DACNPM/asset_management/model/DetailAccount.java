package DACNPM.asset_management.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="detail_account")
public class DetailAccount {
    @Id
    @Column(name="id_account")
    private int idAccount;

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

    public int getIdAccount() {
        return idAccount;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDayOfBirth() {
        return dayOfBirth;
    }

    public String getMail() {
        return mail;
    }

    public int getRole() {
        return role;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDayOfBirth(Date dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(int role) {
        this.role = role;
    }
}
