package com.propify.challenge.controller;

import com.propify.challenge.model.Property;
import com.propify.challenge.model.PropertyReport;
import com.propify.challenge.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/property")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    // API endpoints for CRUD operations on entities of type Property

    @GetMapping("/search")
    public Collection<Property> search(@RequestParam ("minRentPrice") String minRentPrice,
                                       @RequestParam ("maxRentPrice")String maxRentPrice) {
        return propertyService.search(minRentPrice, maxRentPrice);
    }

    @GetMapping("/{id}")
    public Property findById(@PathVariable("id") int id) {
        return propertyService.findById(id);
    }

    @PostMapping
    public void insert(@RequestBody Property property) {
        // TODO: Property attributes must be validated
        propertyService.insert(property);
    }

    @PutMapping
    public void update(@RequestBody Property property) {
        // TODO: Property attributes must be validated
        propertyService.update(property);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        propertyService.delete(id);
    }

    @GetMapping("/report")
    public PropertyReport report() {
        return propertyService.propertyReport();
    }
}
