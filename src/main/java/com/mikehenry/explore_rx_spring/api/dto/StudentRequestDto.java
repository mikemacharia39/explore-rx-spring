package com.mikehenry.explore_rx_spring.api.dto;

import com.mikehenry.explore_rx_spring.domain.enumeration.Gender;
import com.mikehenry.explore_rx_spring.domain.enumeration.LearningScheduleType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
public class StudentRequestDto {
    @NotBlank
    @Size(min = 4, max = 30)
    String firstName;
    @NotBlank
    @Size(min = 4, max = 30)
    String lastName;
    @NotNull
    Gender gender;
    @NotNull
    LocalDate dateOfBirth;
    @NotBlank
    String email;
    @NotNull
    LearningScheduleType scheduleType;
    @NotNull
    AddressDto address;
    @NotEmpty
    List<String> subjects;
    @Min(value = 0)
    BigDecimal totalSpentInBooks;
}
