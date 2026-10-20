package za.ac.cput.itemhivesd2.payment;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class CheckoutController {

    private static final Logger log = LoggerFactory.getLogger(CheckoutController.class);

    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    /** Requires login. Returns {"url": "https://checkout.stripe.com/..."} */
    @PostMapping("/create-checkout-session")
    public ResponseEntity<?> createCheckoutSession(@Valid @RequestBody CreateCheckoutRequest request,
                                                   Authentication auth) {
        try {
            String url = checkoutService.createSession(request, auth.getName());
            return ResponseEntity.ok(Map.of("url", url));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        } catch (StripeException e) {
            // The reason is only written to the server log, never sent to the browser.
            log.error("Stripe checkout failed: {} (code={}, status={})",
                    e.getMessage(), e.getCode(), e.getStatusCode());
            return ResponseEntity.status(502)
                    .body(Map.of("message", "Could not start payment. Please try again."));
        }
    }

    /** Public. Asks Stripe whether this checkout was paid. Plain text: "paid", "unpaid" or "unknown". */
    @GetMapping("/status/{sessionId}")
    public String status(@PathVariable String sessionId) {
        try {
            Session session = Session.retrieve(sessionId);
            return "paid".equals(session.getPaymentStatus()) ? "paid" : "unpaid";
        } catch (StripeException e) {
            log.warn("Stripe status lookup failed: {}", e.getMessage());
            return "unknown";
        }
    }
}