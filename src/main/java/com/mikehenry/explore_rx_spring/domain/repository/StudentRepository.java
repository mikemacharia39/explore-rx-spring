package com.mikehenry.explore_rx_spring.domain.repository;

import com.mikehenry.explore_rx_spring.domain.entity.Student;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface StudentRepository extends ReactiveMongoRepository<Student, String>, StudentRepositoryCustom {

    Flux<Student> findByFirstNameStartsWithOrLastNameStartsWithOrEmailStartsWith(String firstName,
                                                                                 String lastName,
                                                                                 String email);
}
