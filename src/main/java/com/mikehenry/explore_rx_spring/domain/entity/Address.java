package com.mikehenry.explore_rx_spring.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Address {
    private String country;
    private String city;
    private String addressLine1;
    private String postalBox;
    private String postalCode;
}
