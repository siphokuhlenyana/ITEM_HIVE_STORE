package za.ac.cput.FactoryTest;
/*Listing.java
  ListingProduct
  Author: Sinobawo Nkomo(223174602)
  Date: 27 March 2026
 */
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Listing;
import za.ac.cput.factory.ListingFactory;

import static org.junit.jupiter.api.Assertions.*;

class ListingFactoryTest {

    @Test
    void createNewItemListing() {
        Listing listing = ListingFactory.createNewItemListing(
                "22347603",
                "Calculus Book",
                "R180.00",
                "CAT-BOOKS",
                "Brand new"
        );

        assertNotNull(listing);
        assertEquals("New", listing.getCondition());
        assertEquals("CAT-BOOKS", listing.getCategoryId());
        assertEquals("Calculus Book", listing.getTitle());
    }

    @Test
    void createSecondHandListing() {
        Listing listing = ListingFactory.createSecondHandListing(
                "22347603",
                "Laptop Charger",
                "R120.00",
                "CAT-ELECTRONICS",
                "Working fine"
        );

        assertEquals("Used", listing.getCondition());
    }

    @Test
    void createLikeNewListing() {
        Listing listing = ListingFactory.createLikeNewListing(
                "22347603",
                "Calculator",
                "R95.00",
                "CAT-STATIONERY",
                "Almost new"
        );

        assertEquals("Like New", listing.getCondition());
    }

    @Test
    void createFreeItemListing() {
        Listing listing = ListingFactory.createFreeItemListing(
                "22347603",
                "Notes",
                "CAT-NOTES",
                "Free notes"
        );

        assertEquals("0.00", listing.getPrice());
    }

    @Test
    void createDraftListing() {
        Listing listing = ListingFactory.createDraftListing(
                "22347603",
                "Mouse",
                "R75.00"
        );

        assertEquals("Unknown", listing.getCondition());
        assertTrue(listing.getDescription().contains("Draft"));
    }
}