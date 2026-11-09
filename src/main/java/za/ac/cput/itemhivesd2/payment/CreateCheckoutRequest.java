package za.ac.cput.itemhivesd2.payment;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/** What the React cart sends: { "items": [ { "productId": 1, "quantity": 2 } ] } */
public record CreateCheckoutRequest(
        @NotEmpty(message = "Your cart is empty.") @Valid List<Item> items) {

    public record Item(
            @NotNull(message = "Product is required.") Long productId,
            @Min(value = 1, message = "Quantity must be at least 1.")
            @Max(value = 99, message = "Quantity must be 99 or less.") int quantity) {}
}
