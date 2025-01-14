package com.mikehenry.explore_rx_spring.domain.repository;

import com.mikehenry.explore_rx_spring.api.dto.StudentSearchParams;
import com.mikehenry.explore_rx_spring.domain.entity.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentRepositoryCustom {
    Flux<Student> searchStudents(StudentSearchParams searchParams);


    Mono<Student> updateSubjectsById(String id, String[] subjects);
}