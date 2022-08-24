package com.propify.challenge;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PropertyType {

    SINGLE_FAMILY("Single Family"),
    MULTI_FAMILY("Multi-family"),
    CONDOMINIUM("Condominium"),
    TOWNHOUSE("Townhouse");

    private final String type;

}
