package com.mikehenry.explore_rx_spring.domain.repository;

import com.mikehenry.explore_rx_spring.api.dto.StudentSearchParams;
import com.mikehenry.explore_rx_spring.domain.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class StudentRepositoryCustomImpl implements StudentRepositoryCustom {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Flux<Student> searchStudent(StudentSearchParams searchParams,
                                       Pageable pageable) {
        Criteria criteria = new Criteria();

        if (searchParams.firstName() != null) {
            criteria.orOperator(Criteria.where("firstName").regex(searchParams.firstName(), "i"));
        }
        if (searchParams.lastName() != null) {
            criteria.orOperator(Criteria.where("lastName").regex(searchParams.lastName(), "i"));
        }
        if (searchParams.email() != null) {
            criteria.orOperator(Criteria.where("email").regex(searchParams.email(), "i"));
        }
        if (searchParams.gender() != null) {
            criteria.and("gender").is(searchParams.gender());
        }
        if (searchParams.scheduleType() != null) {
            criteria.and("scheduleType").is(searchParams.scheduleType());
        }
        if (searchParams.search() != null) {
            criteria.orOperator(
                    Criteria.where("firstName").regex(searchParams.search(), "i"),
                    Criteria.where("lastName").regex(searchParams.search(), "i"),
                    Criteria.where("email").regex(searchParams.search(), "i")
            );
        }

        Query query = new Query(criteria).with(pageable);
        return reactiveMongoTemplate.find(query, Student.class);
    }

    @Override
    public Mono<Student> updateSubjectsById(String id, String[] subjects) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update().set("subjects", subjects);
        return reactiveMongoTemplate.findAndModify(query, update, Student.class);
    }
}
