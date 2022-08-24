package com.propify.challenge.model;

import lombok.Data;

import java.util.Map;

@Data
public class PropertyReport {

    Integer totalQuantity;

    Map<PropertyType, Integer> quantityPerType;

    double averageRentPrice;

    Integer illinoisQuantity;
}
