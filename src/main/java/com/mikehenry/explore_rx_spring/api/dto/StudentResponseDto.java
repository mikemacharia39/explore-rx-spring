package com.mikehenry.explore_rx_spring.api.dto;

import com.mikehenry.explore_rx_spring.domain.enumeration.Gender;
import com.mikehenry.explore_rx_spring.domain.enumeration.LearningScheduleType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
public class StudentResponseDto {
    String id;
    String firstName;
    String lastName;
    Gender gender;
    LocalDate dateOfBirth;
    String email;
    LearningScheduleType scheduleType;
    AddressDto address;
    List<String> subjects;
    BigDecimal totalSpentInBooks;
    Instant dateCreated;
}

