package com.propify.challenge.utils;

import com.propify.challenge.model.Property;

public interface PropertyValidator {

     static void validateInsert(Property property){
        //assert property fields are correct for the new insert or else throw new exception for the controller
        // Objects.isNull(property.getId()))

    };
     static void validateUpdate(Property property){

        //assert property fields are correct for the new insert or else throw new exception for the controller

    };
}
