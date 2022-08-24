package com.propify.challenge.model;

import lombok.Data;

import java.util.Map;

@Data
public class PropertyReport {

    Integer totalQuantity;

    Map<PropertyType, Long> quantityPerType;

    double averageRentPrice;

    Long illinoisQuantity;
}
