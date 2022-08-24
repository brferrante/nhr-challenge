package com.propify.challenge.controller;

import com.propify.challenge.model.Property;
import com.propify.challenge.model.PropertyReport;
import com.propify.challenge.service.PropertyService;
import com.propify.challenge.utils.PropertyValidator;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.InvalidPropertiesFormatException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/properties")
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
        PropertyValidator.validateInsert(property);
        propertyService.insert(property);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody Property property) {
        PropertyValidator.validateUpdate(property);
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


    @ControllerAdvice
    public static class Handler {

        @ExceptionHandler(Exception.class)
        public ResponseEntity<Object> handle(Exception ex,
                                             HttpServletRequest request, HttpServletResponse response) {
            if (ex instanceof NotFoundException || ex instanceof InvalidPropertiesFormatException) {
                return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
