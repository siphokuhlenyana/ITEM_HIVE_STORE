package za.ac.cput.itemhivesd2.service;

/*ListingService.java
  ListingProduct
  Author: Sinobawo Nkomo(223174602)
  Date: 12 July 2026
 */

import org.springframework.stereotype.Service;
import za.ac.cput.itemhivesd2.domain.Listing;
import za.ac.cput.itemhivesd2.repository.ListingRepository;

import java.util.List;

@Service
public class ListingService implements IListingService {

    private final ListingRepository repository;

    public ListingService(ListingRepository repository) {
        this.repository = repository;
    }

    @Override
    public Listing create(Listing listing) {
        return repository.save(listing);
    }

    @Override
    public Listing read(String listingId) {
        return repository.findById(listingId).orElse(null);
    }

    @Override
    public Listing update(Listing listing) {
        return repository.save(listing);
    }

    @Override
    public boolean delete(String listingId) {
        if (!repository.existsById(listingId)) return false;
        repository.deleteById(listingId);
        return true;
    }

    @Override
    public List<Listing> getAll() {
        return repository.findAll();
    }
}