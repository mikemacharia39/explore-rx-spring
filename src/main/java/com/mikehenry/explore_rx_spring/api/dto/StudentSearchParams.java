package com.mikehenry.explore_rx_spring.api.dto;

import com.mikehenry.explore_rx_spring.domain.enumeration.Gender;
import com.mikehenry.explore_rx_spring.domain.enumeration.LearningScheduleType;
import lombok.Builder;

import java.util.List;

@Builder
public record StudentSearchParams(
        List<String> ids,
        String firstName,
        String lastName,
        String email,
        Gender gender,
        LearningScheduleType scheduleType,
        String search
) {
}
