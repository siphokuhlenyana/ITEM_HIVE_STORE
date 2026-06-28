package za.ac.cput.factory;
/*Listing.java
  ListingProduct
  Author: Sinobawo Nkomo(223174602)
  Date: 27 March 2026
 */
import za.ac.cput.domain.Listing;

public class ListingFactory {

    public static Listing createNewItemListing(
            String studentId,
            String title,
            String price,
            String categoryId,
            String description) {

        return new Listing.Builder(studentId, title, price)
                .categoryId(categoryId)
                .description(description)
                .condition("New")
                .build();
    }

    public static Listing createSecondHandListing(
            String studentId,
            String title,
            String price,
            String categoryId,
            String description) {

        return new Listing.Builder(studentId, title, price)
                .categoryId(categoryId)
                .description(description)
                .condition("Used")
                .build();
    }

    public static Listing createLikeNewListing(
            String studentId,
            String title,
            String price,
            String categoryId,
            String description) {

        return new Listing.Builder(studentId, title, price)
                .categoryId(categoryId)
                .description(description)
                .condition("Like New")
                .build();
    }

    public static Listing createFreeItemListing(
            String studentId,
            String title,
            String categoryId,
            String description) {

        return new Listing.Builder(studentId, title, "0.00")
                .categoryId(categoryId)
                .description(description)
                .condition("Used")
                .build();
    }

    public static Listing createDraftListing(
            String studentId,
            String title,
            String price) {

        return new Listing.Builder(studentId, title, price)
                .description("Draft – details coming soon.")
                .condition("Unknown")
                .build();
    }
}

