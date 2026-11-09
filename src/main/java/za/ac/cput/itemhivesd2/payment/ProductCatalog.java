package za.ac.cput.itemhivesd2.payment;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

/**
 * Prices are decided here on the server, never taken from the browser.
 * Keep this in sync with frontend/src/data/Product.js.
 */
public final class ProductCatalog {

    public record Product(long id, String name, BigDecimal price) {}

    private static final Map<Long, Product> PRODUCTS = Map.ofEntries(
            p(1, "Rainbow Mouse", "200.00"),
            p(2, "Grey Laptop", "5000.00"),
            p(3, "White Printer", "2500.00"),
            p(4, "Computer SSD", "250.00"),
            p(5, "White Headphones", "300.00"),
            p(6, "Black Headphones", "250.00"),
            p(7, "Rainbow Printer", "600.00"),
            p(8, "Rainbow USB", "100.00"),
            p(9, "Black Monitor", "7000.00"),
            p(10, "Black Laptop", "6500.00"),
            p(11, "Rainbow Headphones", "7000.00"),
            p(12, "Keyboard & Mouse", "3500.00"),
            p(13, "Modern Printer", "5000.00"),
            p(14, "3D Printer", "10000.00"),
            p(15, "Sleek Monitor", "4000.00"),
            p(16, "Apple Monitor", "12000.00"),
            p(17, "White SSD", "400.00"),
            p(18, "Apple Monitor (Large)", "11000.00")
    );

    private static Map.Entry<Long, Product> p(long id, String name, String price) {
        return Map.entry(id, new Product(id, name, new BigDecimal(price)));
    }

    public static Optional<Product> find(long id) {
        return Optional.ofNullable(PRODUCTS.get(id));
    }

    private ProductCatalog() {}
}
