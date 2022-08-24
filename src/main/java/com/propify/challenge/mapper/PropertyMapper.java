package com.propify.challenge.mapper;

import com.propify.challenge.model.Property;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

@Mapper
public interface PropertyMapper {

     void insert(Property property);

     Set<Property> search(String minRentPrice, String maxRentPrice);

     Property findById(Integer id);

     void update(Property property);

     Runnable delete(Integer id);
}
