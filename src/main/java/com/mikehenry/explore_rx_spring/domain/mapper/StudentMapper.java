package com.mikehenry.explore_rx_spring.domain.mapper;

import com.mikehenry.explore_rx_spring.api.dto.AddressDto;
import com.mikehenry.explore_rx_spring.api.dto.StudentRequestDto;
import com.mikehenry.explore_rx_spring.api.dto.StudentResponseDto;
import com.mikehenry.explore_rx_spring.domain.entity.Address;
import com.mikehenry.explore_rx_spring.domain.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public static Address toEntity(AddressDto addressDto) {
        return Address.builder()
                .country(addressDto.getCountry())
                .city(addressDto.getCity())
                .addressLine1(addressDto.getAddressLine1())
                .postalBox(addressDto.getPostalBox())
                .postalCode(addressDto.getPostalCode())
                .build();
    }

    public static AddressDto toDto(Address address) {
        return AddressDto.builder()
                .country(address.getCountry())
                .city(address.getCity())
                .addressLine1(address.getAddressLine1())
                .postalBox(address.getPostalBox())
                .postalCode(address.getPostalCode())
                .build();
    }

    public Student toEntity(StudentRequestDto studentDto) {
        return Student.builder()
                .firstName(studentDto.getFirstName())
                .lastName(studentDto.getLastName())
                .address(toEntity(studentDto.getAddress()))
                .dateOfBirth(studentDto.getDateOfBirth())
                .gender(studentDto.getGender())
                .scheduleType(studentDto.getScheduleType())
                .totalSpentInBooks(studentDto.getTotalSpentInBooks())
                .email(studentDto.getEmail())
                .subjects(studentDto.getSubjects())
                .build();
    }

    public StudentResponseDto toResponseDto(Student student) {
        return StudentResponseDto.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .address(student.getAddress() == null ? null : toDto(student.getAddress()))
                .dateOfBirth(student.getDateOfBirth())
                .gender(student.getGender())
                .email(student.getEmail())
                .scheduleType(student.getScheduleType())
                .subjects(student.getSubjects())
                .totalSpentInBooks(student.getTotalSpentInBooks())
                .dateCreated(student.getDateCreated())
                .build();
    }
}
