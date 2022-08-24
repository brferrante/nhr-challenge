package com.propify.challenge.service;

import com.propify.challenge.mapper.AddressMapper;
import com.propify.challenge.mapper.PropertyMapper;
import com.propify.challenge.model.Property;
import com.propify.challenge.model.PropertyReport;
import com.propify.challenge.model.States;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.BiConsumer;
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

    @SneakyThrows
    public void delete(int id){
        CompletableFuture<Void> future = CompletableFuture.runAsync(propertyMapper.delete(id))
                .thenRun(()->  alertService.sendPropertyDeletedAlert(id));
        future.thenRun( () -> System.out.println("DELETED: " + id)).get(300, TimeUnit.MILLISECONDS);
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
