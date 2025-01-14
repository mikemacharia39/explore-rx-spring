package com.mikehenry.explore_rx_spring.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    private String country;
    private String city;
    private String addressLine1;
    private String postalBox;
    private String postalCode;
}
