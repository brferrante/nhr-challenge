package com.propify.challenge.service;

import com.propify.challenge.mapper.AddressMapper;
import com.propify.challenge.mapper.PropertyMapper;
import com.propify.challenge.model.Property;
import com.propify.challenge.model.PropertyReport;
import com.propify.challenge.model.States;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyMapper propertyMapper;

    private final AddressMapper addressMapper;

    private final AlertService alertService;

    public Collection<Property> search(String minRentPrice, String maxRentPrice) {
        return propertyMapper.search(minRentPrice, maxRentPrice);
    }

    public Property findById(int id) {
        return propertyMapper.findById(id);
    }

    public void insert(Property property) {
        propertyMapper.insert(property);
        System.out.println("CREATED: " + property.id);
    }

    public void update(Property property) {
        propertyMapper.update(property);
        System.out.println("UPDATED: " + property.id);
    }

    public void delete(int id) {
        //TODO: Wrap in completableFuture to trigger the alert on completion
        propertyMapper.delete(id);
        System.out.println("DELETED: " + id);

        alertService.sendPropertyDeletedAlert(id);
        // TODO: Sending the alert should be non-blocking (asynchronous)
        //  Extra points for only sending the alert when/if the transaction is committed
    }

    public PropertyReport propertyReport() {
        var allProperties = propertyMapper.search(null, null);
        var propertyReport = new PropertyReport();

        // Calculate total quantity
         propertyReport.setTotalQuantity( allProperties.size());

        // Calculate the quantity of each type, 0 if there is no properties.

        propertyReport.setQuantityPerType(
                allProperties.stream()
                        .collect(Collectors.groupingBy(Property::getType, Collectors.counting())));

        // Calculate the average rent price (exclude the properties without rent price or with rent price = 0)
        propertyReport.setAverageRentPrice(allProperties.stream()
                .filter(property -> (Objects.nonNull(property.getRentPrice())&& 0.00 < property.getRentPrice()))
                .mapToDouble(Property::getRentPrice).average().orElse(0.00));

        // Calculate the quantity of properties in the state of Illinois (IL)
        propertyReport.setIllinoisQuantity(allProperties.stream()
                .filter(property -> property.getAddress().state.equals(States.ILLINOIS.getStateCode())).count());

        return propertyReport;
    }
}
