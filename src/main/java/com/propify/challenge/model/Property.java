package com.propify.challenge.model;

import lombok.Data;

@Data
public class Property {

    public Integer id; // must be null for INSERT and not null for UPDATE

    public String createTime;

    public PropertyType type;

    public Double rentPrice; // must be greater than 0, 2 decimal places

    public Address address; // must not be null

    public String emailAddress; // must be a valid email address

    public String code; // not null, only uppercase letters or numbers, 10 characters
}
