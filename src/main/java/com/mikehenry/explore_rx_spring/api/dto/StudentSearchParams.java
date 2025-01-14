package com.mikehenry.explore_rx_spring.api.dto;

import com.mikehenry.explore_rx_spring.domain.enumeration.Gender;
import com.mikehenry.explore_rx_spring.domain.enumeration.LearningScheduleType;
import lombok.Builder;

@Builder
public record StudentSearchParams(
        String firstName,
        String lastName,
        String email,
        Gender gender,
        LearningScheduleType scheduleType,
        String search
) {
}
