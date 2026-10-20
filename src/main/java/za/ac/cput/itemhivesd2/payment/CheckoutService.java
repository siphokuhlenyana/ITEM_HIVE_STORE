package za.ac.cput.itemhivesd2.payment;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CheckoutService {

    private static final BigDecimal DISCOUNT_FACTOR = new BigDecimal("0.80"); // 20% off, same as the cart page
    private static final long DELIVERY_FEE_CENTS = 1500;                      // R15.00, same as the cart page

    private final String frontendUrl;

    public CheckoutService(@Value("${app.frontend.url}") String frontendUrl,
                           @Value("${stripe.secret.key}") String stripeSecretKey) {
        this.frontendUrl = frontendUrl;
        Stripe.apiKey = stripeSecretKey;
    }

    /** Creates a Stripe Checkout page for the cart and returns its URL. */
    public String createSession(CreateCheckoutRequest request, String customerEmail) throws StripeException {
        SessionCreateParams.Builder params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(frontendUrl + "/payment-success?session_id={CHECKOUT_SESSION_ID}")
                .setCancelUrl(frontendUrl + "/payment-cancel")
                .setCustomerEmail(customerEmail)
                .putMetadata("customerEmail", customerEmail);

        for (CreateCheckoutRequest.Item item : request.items()) {
            ProductCatalog.Product product = ProductCatalog.find(item.productId())
                    .orElseThrow(() -> new IllegalArgumentException("Unknown product: " + item.productId()));
            long unitCents = toCents(product.price().multiply(DISCOUNT_FACTOR));
            params.addLineItem(lineItem(product.name() + " (20% off)", unitCents, item.quantity()));
        }
        params.addLineItem(lineItem("Delivery fee", DELIVERY_FEE_CENTS, 1));

        return Session.create(params.build()).getUrl();
    }

    private static long toCents(BigDecimal rands) {
        return rands.movePointRight(2).setScale(0, RoundingMode.HALF_UP).longValueExact();
    }

    private static SessionCreateParams.LineItem lineItem(String name, long unitAmountCents, long quantity) {
        return SessionCreateParams.LineItem.builder()
                .setQuantity(quantity)
                .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                        .setCurrency("zar")
                        .setUnitAmount(unitAmountCents)
                        .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                .setName(name)
                                .build())
                        .build())
                .build();
    }
}
