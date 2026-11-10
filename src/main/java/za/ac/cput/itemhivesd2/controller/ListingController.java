package za.ac.cput.itemhivesd2.controller;


import org.springframework.web.bind.annotation.*;
import za.ac.cput.itemhivesd2.domain.Listing;
import za.ac.cput.itemhivesd2.service.ListingService;

import java.util.List;

@RestController
@RequestMapping("/api/listing")
public class ListingController {

    private final ListingService service;

    public ListingController(ListingService service) {
        this.service = service;
    }

    @PostMapping
    public Listing create(@RequestBody Listing listing){
        return service.create(listing);
    }

   @GetMapping("/{listingId}")
    public Listing read(@PathVariable String listingId) {
        return service.read(listingId);
   }

   @GetMapping
    public List<Listing> getAll(){
        return service.getAll();
   }

   @PutMapping
    public Listing update(@RequestBody Listing listing){
        return service.update(listing);
   }

   @DeleteMapping("/{listingId}")
    public boolean delete(@PathVariable String listingId){
        return service.delete(listingId);
   }
}
