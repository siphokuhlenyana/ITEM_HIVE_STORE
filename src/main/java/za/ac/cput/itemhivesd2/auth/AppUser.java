package za.ac.cput.itemhivesd2.auth;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "app_users")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    private String firstName;
    private String lastName;
    private String studentNumber;
    private String phone;
    private String address;

    private String role = "STUDENT";
    private boolean verified = false;

    private String verificationCodeHash;
    private Instant verificationExpiry;
    private int verificationAttempts = 0;

    public Long getId() { return id; }
    public String getEmail() { return email; }
    public void setEmail(String v) { this.email = v; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String v) { this.passwordHash = v; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String v) { this.firstName = v; }
    public String getLastName() { return lastName; }
    public void setLastName(String v) { this.lastName = v; }
    public String getStudentNumber() { return studentNumber; }
    public void setStudentNumber(String v) { this.studentNumber = v; }
    public String getPhone() { return phone; }
    public void setPhone(String v) { this.phone = v; }
    public String getAddress() { return address; }
    public void setAddress(String v) { this.address = v; }
    public String getRole() { return role; }
    public void setRole(String v) { this.role = v; }
    public boolean isVerified() { return verified; }
    public void setVerified(boolean v) { this.verified = v; }
    public String getVerificationCodeHash() { return verificationCodeHash; }
    public void setVerificationCodeHash(String v) { this.verificationCodeHash = v; }
    public Instant getVerificationExpiry() { return verificationExpiry; }
    public void setVerificationExpiry(Instant v) { this.verificationExpiry = v; }
    public int getVerificationAttempts() { return verificationAttempts; }
    public void setVerificationAttempts(int v) { this.verificationAttempts = v; }

    public String getFullName() { return firstName + " " + lastName; }
}
