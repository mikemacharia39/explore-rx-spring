package com.mikehenry.explore_rx_spring.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record AddressDto(
        @NotBlank
        String country,
        @NotBlank
        String city,
        @NotBlank
        String addressLine1,
        @NotBlank
        String postalBox,
        @NotBlank
        String postalCode
) {
}
