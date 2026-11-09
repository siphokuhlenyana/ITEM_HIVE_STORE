package za.ac.cput.itemhivesd2.auth;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    record ProfileResponse(String firstName, String lastName, String email,
                           String studentNumber, String phone, String address) {
        static ProfileResponse of(AppUser u) {
            return new ProfileResponse(u.getFirstName(), u.getLastName(), u.getEmail(),
                    u.getStudentNumber(), u.getPhone(), u.getAddress());
        }
    }

    record UpdateProfileRequest(
            @NotBlank String firstName,
            @NotBlank String lastName,
            @Pattern(regexp = "\\+?\\d{9,12}", message = "Enter a valid phone number.") String phone,
            @NotBlank String address) {}

    private final UserRepository users;

    public UserController(UserRepository users) {
        this.users = users;
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(Authentication auth) {
        return users.findByEmailIgnoreCase(auth.getName())
                .<ResponseEntity<?>>map(u -> ResponseEntity.ok(ProfileResponse.of(u)))
                .orElse(ResponseEntity.status(401).body(Map.of("message", "Please log in again.")));
    }

    @PutMapping("/me")
    public ResponseEntity<?> update(Authentication auth, @Valid @RequestBody UpdateProfileRequest r) {
        AppUser u = users.findByEmailIgnoreCase(auth.getName()).orElse(null);
        if (u == null) return ResponseEntity.status(401).body(Map.of("message", "Please log in again."));

        u.setFirstName(r.firstName().trim());
        u.setLastName(r.lastName().trim());
        u.setPhone(r.phone().trim());
        u.setAddress(r.address().trim());
        users.save(u);
        return ResponseEntity.ok(ProfileResponse.of(u));
    }
}
