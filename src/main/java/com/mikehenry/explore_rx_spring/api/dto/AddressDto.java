package com.mikehenry.explore_rx_spring.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class AddressDto {
    @NotBlank
    String country;
    @NotBlank
    String city;
    @NotBlank
    String addressLine1;
    @NotBlank
    String postalBox;
    @NotBlank
    String postalCode;
}
