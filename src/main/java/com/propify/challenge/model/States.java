package com.propify.challenge.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum States {
    ILLINOIS("IL");
    private final String stateCode;
}
