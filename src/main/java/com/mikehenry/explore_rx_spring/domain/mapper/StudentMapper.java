package com.mikehenry.explore_rx_spring.domain.mapper;

import com.mikehenry.explore_rx_spring.api.dto.StudentRequestDto;
import com.mikehenry.explore_rx_spring.api.dto.StudentResponseDto;
import com.mikehenry.explore_rx_spring.domain.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {AddressMapper.class})
public abstract class StudentMapper {

    @Mapping(target = "id", ignore = true)
    public abstract Student toEntity(StudentRequestDto studentDto);

    public abstract StudentResponseDto toResponseDto(Student student);
}
