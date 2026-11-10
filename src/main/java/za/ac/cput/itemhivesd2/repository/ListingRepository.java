package za.ac.cput.itemhivesd2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.itemhivesd2.domain.Listing;

public interface ListingRepository extends JpaRepository<Listing, String> {
}