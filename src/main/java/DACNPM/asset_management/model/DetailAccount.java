package DACNPM.asset_management.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private int idAccount;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="day_of_birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd") // For form submissions
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") // For JSON processing
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
