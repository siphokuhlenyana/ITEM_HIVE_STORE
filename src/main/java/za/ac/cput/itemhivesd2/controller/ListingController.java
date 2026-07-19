package za.ac.cput.listing.controller;


import org.springframework.web.bind.annotation.*;
import za.ac.cput.listing.domain.Listing;
import za.ac.cput.listing.service.ListingService;

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

   @GetMapping("{/ListingId}")
    public Listing read(@PathVariable String ListingId) {
        return service.read(ListingId);
   }

   @GetMapping
    public List<Listing> getAll(){
        return service.getAll();
   }

   @PutMapping
    public Listing update(@RequestBody Listing listing){
        return service.update(listing);
   }

   @DeleteMapping("/ListingId")
    public boolean delete(@PathVariable String ListingId){
        return service.delete(ListingId);
   }
}
