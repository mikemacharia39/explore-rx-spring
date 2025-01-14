package com.mikehenry.explore_rx_spring.api.dto;

import com.mikehenry.explore_rx_spring.domain.enumeration.Gender;
import com.mikehenry.explore_rx_spring.domain.enumeration.LearningScheduleType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record StudentResponseDto(
        Long id,
        String firstName,
        String lastName,
        Gender gender,
        LocalDate dateOfBirth,
        String email,
        LearningScheduleType scheduleType,
        AddressDto address,
        List<String> subjects,
        BigDecimal totalSpentInBooks
){}
