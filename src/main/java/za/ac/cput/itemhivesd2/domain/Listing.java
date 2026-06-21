package za.ac.cput.listing.domain;
/* Listing.java
   ListingProduct
   Author: Sinobawo Nkomo (223174602)
   Date: 21 June 2026
*/
import jakarta.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "listing")
public class Listing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "listing_id", nullable = false, unique = true)
    private final String listingId;

    @Column(name = "student_id", nullable = false)
    private final String studentId;

    @Column(name = "category_id", nullable = false)
    private final String categoryId;

    @Column(nullable = false)
    private final String title;

    @Column(length = 1000)
    private final String description;

    @Column(nullable = false)
    private final String price;

    @Column(nullable = false)
    private final String condition;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_posted", nullable = false)
    private final Date datePosted;

    protected Listing() {
        // Required by JPA
        this.listingId = null;
        this.studentId = null;
        this.categoryId = null;
        this.title = null;
        this.description = null;
        this.price = null;
        this.condition = null;
        this.datePosted = null;
    }

    private Listing(Builder builder) {
        this.listingId = builder.listingId;
        this.studentId = builder.studentId;
        this.categoryId = builder.categoryId;
        this.title = builder.title;
        this.description = builder.description;
        this.price = builder.price;
        this.condition = builder.condition;
        this.datePosted = builder.datePosted;
    }

    // Getters
    public Long getId() { return id; }
    public String getListingId() { return listingId; }
    public String getStudentId() { return studentId; }
    public String getCategoryId() { return categoryId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getPrice() { return price; }
    public String getCondition() { return condition; }
    public Date getDatePosted() { return datePosted; }

    // Builder pattern
    public static class Builder {
        private String listingId = UUID.randomUUID().toString();
        private String studentId;
        private String categoryId;
        private String title;
        private String description;
        private String price;
        private String condition;
        private Date datePosted = new Date();

        public Builder studentId(String studentId) {
            this.studentId = studentId;
            return this;
        }

        public Builder categoryId(String categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder price(String price) {
            this.price = price;
            return this;
        }

        public Builder condition(String condition) {
            this.condition = condition;
            return this;
        }

        public Listing build() {
            return new Listing(this);
        }
    }
}
