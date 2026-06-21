package za.ac.cput.listing.domain;
/*Listing.java
  ListingProduct
  Author: Sinobawo Nkomo(223174602)
  Date: 21 June 2026
 */
import java.util.Date;
import java.util.UUID;

public class Listing {

    private final String  listingId;
    private final String  studentId;
    private final String  categoryId;
    private final String  title;
    private final String  description;
    private final String  price;
    private final String  condition;
    private final Date    datePosted;

    private Listing(Builder builder) {
        this.listingId   = builder.listingId;
        this.studentId   = builder.studentId;
        this.categoryId  = builder.categoryId;
        this.title       = builder.title;
        this.description = builder.description;
        this.price       = builder.price;
        this.condition   = builder.condition;
        this.datePosted  = builder.datePosted;
    }


    public String getListingId(){
        return listingId;
    }
    public String getStudentId(){
        return studentId;
    }
    public String getCategoryId(){
        return categoryId;
    }
    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
    public String getPrice(){
        return price;
    }
    public String getCondition(){
        return condition;
    }
    public Date   getDatePosted(){
        return datePosted;
    }

    @Override
    public String toString() {
        return "Listing{" +
                "id='"          + listingId   + '\'' +
                ", title='"     + title       + '\'' +
                ", price='"     + price       + '\'' +
                ", condition='" + condition   + '\'' +
                ", posted="     + datePosted  +
                '}';
    }
    public static class Builder {
        private final String studentId;
        private final String title;
        private final String price;


        private String listingId   = UUID.randomUUID().toString();
        private String categoryId  = null;
        private String description = "";
        private String condition   = "Used";
        private Date   datePosted  = new Date();

        public Builder(String studentId, String title, String price) {
            if (studentId == null || studentId.isBlank())
                throw new IllegalArgumentException("studentId is required");
            if (title == null || title.isBlank())
                throw new IllegalArgumentException("title is required");
            if (price == null || price.isBlank())
                throw new IllegalArgumentException("price is required");

            this.studentId = studentId;
            this.title     = title;
            this.price     = price;
        }

        public Builder listingId(String listingId) {
            this.listingId = listingId;
            return this;
        }

        public Builder categoryId(String categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder condition(String condition) {
            this.condition = condition;
            return this;
        }

        public Builder datePosted(Date datePosted) {
            this.datePosted = datePosted;
            return this;
        }

        public Listing build() {
            return new Listing(this);
        }
    }
}
