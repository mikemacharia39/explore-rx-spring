package com.mikehenry.explore_rx_spring.domain.mapper;

import com.mikehenry.explore_rx_spring.api.dto.AddressDto;
import com.mikehenry.explore_rx_spring.domain.entity.Address;
import org.mapstruct.Mapper;

@Mapper
public abstract class AddressMapper {

    public abstract Address toEntity(AddressDto addressDto);

    public abstract AddressDto toDto(Address address);
}
