package com.propify.challenge.controller;

import com.propify.challenge.model.Property;
import com.propify.challenge.model.PropertyReport;
import com.propify.challenge.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/property")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    // API endpoints for CRUD operations on entities of type Property

    @GetMapping("/search")
    public ResponseEntity<Collection<Property>> search(@RequestParam ("minRentPrice") String minRentPrice,
                                       @RequestParam ("maxRentPrice")String maxRentPrice) {
        return ResponseEntity.ok(propertyService.search(minRentPrice, maxRentPrice));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Property> findById(@PathVariable("id") int id) {
        return ResponseEntity.ok(propertyService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Property property) {
        // TODO: Property attributes must be validated
        propertyService.insert(property);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody Property property) {
        // TODO: Property attributes must be validated
        propertyService.update(property);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        propertyService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/report")
    public ResponseEntity<PropertyReport> report() {
        return ResponseEntity.ok(propertyService.propertyReport());
    }
}
