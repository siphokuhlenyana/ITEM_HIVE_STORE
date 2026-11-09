package za.ac.cput.itemhivesd2.auth;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final int MAX_ATTEMPTS = 5;
    private static final SecureRandom RANDOM = new SecureRandom();

    record RegisterRequest(
            @NotBlank String firstName,
            @NotBlank String lastName,
            @Pattern(regexp = "\\d{9}", message = "Student number must be 9 digits.") String studentNumber,
            @Email @NotBlank String email,
            @Pattern(regexp = "\\+?\\d{9,12}", message = "Enter a valid phone number.") String phone,
            @NotBlank String address,
            @Size(min = 6, max = 72, message = "Password must be 6 to 72 characters.") String password) {}

    record VerifyRequest(@Email @NotBlank String email, @Pattern(regexp = "\\d{6}") String code) {}
    record EmailRequest(@Email @NotBlank String email) {}
    record LoginRequest(@Email @NotBlank String email, @NotBlank String password) {}

    private final UserRepository users;
    private final PasswordEncoder encoder;
    private final JwtService jwt;
    private final EmailService mail;

    public AuthController(UserRepository users, PasswordEncoder encoder, JwtService jwt, EmailService mail) {
        this.users = users;
        this.encoder = encoder;
        this.jwt = jwt;
        this.mail = mail;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest r) {
        String email = r.email().trim().toLowerCase();
        if (users.existsByEmailIgnoreCase(email)) {
            return ResponseEntity.status(409).body(Map.of("message", "An account with this email already exists."));
        }

        AppUser u = new AppUser();
        u.setEmail(email);
        u.setPasswordHash(encoder.encode(r.password()));
        u.setFirstName(r.firstName().trim());
        u.setLastName(r.lastName().trim());
        u.setStudentNumber(r.studentNumber());
        u.setPhone(r.phone().trim());
        u.setAddress(r.address().trim());

        String code = issueCode(u);
        users.save(u);
        mail.sendVerificationCode(email, code);
        return ResponseEntity.status(201).body(Map.of("message", "Verification code sent."));
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verify(@Valid @RequestBody VerifyRequest r) {
        AppUser u = users.findByEmailIgnoreCase(r.email().trim()).orElse(null);
        if (u == null) return bad("Incorrect code.");
        if (u.isVerified()) return ResponseEntity.ok(Map.of("message", "Email already verified."));

        if (u.getVerificationCodeHash() == null || u.getVerificationExpiry() == null
                || Instant.now().isAfter(u.getVerificationExpiry())) {
            return bad("This code has expired. Request a new one.");
        }
        if (u.getVerificationAttempts() >= MAX_ATTEMPTS) {
            return ResponseEntity.status(429).body(Map.of("message", "Too many attempts. Request a new code."));
        }
        if (!encoder.matches(r.code(), u.getVerificationCodeHash())) {
            u.setVerificationAttempts(u.getVerificationAttempts() + 1);
            users.save(u);
            return bad("Incorrect code.");
        }

        u.setVerified(true);
        u.setVerificationCodeHash(null);
        u.setVerificationExpiry(null);
        u.setVerificationAttempts(0);
        users.save(u);
        return ResponseEntity.ok(Map.of("message", "Email verified."));
    }

    @PostMapping("/resend")
    public ResponseEntity<?> resend(@Valid @RequestBody EmailRequest r) {
        // Same response whether or not the account exists, so emails can't be probed.
        users.findByEmailIgnoreCase(r.email().trim())
                .filter(u -> !u.isVerified())
                .ifPresent(u -> {
                    String code = issueCode(u);
                    users.save(u);
                    mail.sendVerificationCode(u.getEmail(), code);
                });
        return ResponseEntity.ok(Map.of("message", "If the account exists, a new code was sent."));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest r) {
        AppUser u = users.findByEmailIgnoreCase(r.email().trim()).orElse(null);
        if (u == null || !encoder.matches(r.password(), u.getPasswordHash())) {
            return ResponseEntity.status(401).body(Map.of("message", "Incorrect email or password."));
        }
        if (!u.isVerified()) {
            return ResponseEntity.status(403).body(Map.of("message", "Verify your email first."));
        }
        return ResponseEntity.ok(Map.of(
                "token", jwt.generate(u),
                "email", u.getEmail(),
                "name", u.getFullName(),
                "role", u.getRole()));
    }

    private String issueCode(AppUser u) {
        String code = String.format("%06d", RANDOM.nextInt(1_000_000));
        u.setVerificationCodeHash(encoder.encode(code));
        u.setVerificationExpiry(Instant.now().plus(15, ChronoUnit.MINUTES));
        u.setVerificationAttempts(0);
        return code;
    }

    private ResponseEntity<?> bad(String message) {
        return ResponseEntity.badRequest().body(Map.of("message", message));
    }
}
