package za.ac.cput.listing.service;

/*IService.java
  ListingProduct
  Author: Sinobawo Nkomo(223174602)
  Date: 12 July 2026
 */


import za.ac.cput.listing.domain.Listing;

import java.util.List;

public interface IListingService{
    Listing create(Listing listing);

    Listing read(String listingId);

    Listing update(Listing listing);

    boolean delete(String listingId);

    List<Listing> getAll();
}
