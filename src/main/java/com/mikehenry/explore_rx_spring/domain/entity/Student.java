package com.mikehenry.explore_rx_spring.domain.entity;


import com.mikehenry.explore_rx_spring.domain.enumeration.Gender;
import com.mikehenry.explore_rx_spring.domain.enumeration.LearningScheduleType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Document(collection = "students")
public class Student {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDate dateOfBirth;
    @Indexed(unique = true, name = "student-email-index")
    private String email;
    @Builder.Default
    private LearningScheduleType scheduleType = LearningScheduleType.FULL_TIME;
    private Address address;
    private List<String> subjects;
    private BigDecimal totalSpentInBooks;
    @Builder.Default
    private Instant dateCreated = Instant.now();
}
