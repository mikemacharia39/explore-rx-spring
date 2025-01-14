package com.mikehenry.explore_rx_spring.domain.repository;

import com.mikehenry.explore_rx_spring.domain.entity.Student;
import com.mikehenry.explore_rx_spring.domain.enumeration.Gender;
import com.mikehenry.explore_rx_spring.domain.enumeration.LearningScheduleType;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentRepositoryCustom {
    Flux<Student> searchStudent(String firstName,
                                String lastName,
                                String email,
                                Gender gender,
                                LearningScheduleType scheduleType,
                                String search,
                                Pageable pageable);


    Mono<Student> updateSubjectsById(String id, String[] subjects);
}