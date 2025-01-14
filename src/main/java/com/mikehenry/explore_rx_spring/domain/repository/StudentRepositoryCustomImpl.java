package com.mikehenry.explore_rx_spring.domain.repository;

import com.mikehenry.explore_rx_spring.domain.entity.Student;
import com.mikehenry.explore_rx_spring.domain.enumeration.Gender;
import com.mikehenry.explore_rx_spring.domain.enumeration.LearningScheduleType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class StudentRepositoryCustomImpl implements StudentRepositoryCustom {

    private final ReactiveMongoTemplate mongoTemplate;

    public StudentRepositoryCustomImpl(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Flux<Student> searchStudent(String firstName,
                                       String lastName,
                                       String email,
                                       Gender gender,
                                       LearningScheduleType scheduleType,
                                       String search,
                                       Pageable pageable) {
        Criteria criteria = new Criteria();

        if (firstName != null) {
            criteria.orOperator(Criteria.where("firstName").regex(firstName, "i"));
        }
        if (lastName != null) {
            criteria.orOperator(Criteria.where("lastName").regex(lastName, "i"));
        }
        if (email != null) {
            criteria.orOperator(Criteria.where("email").regex(email, "i"));
        }
        if (gender != null) {
            criteria.and("gender").is(gender);
        }
        if (scheduleType != null) {
            criteria.and("scheduleType").is(scheduleType);
        }
        if (search != null) {
            criteria.orOperator(
                    Criteria.where("firstName").regex(search, "i"),
                    Criteria.where("lastName").regex(search, "i"),
                    Criteria.where("email").regex(search, "i")
            );
        }

        Query query = new Query(criteria).with(pageable);
        return mongoTemplate.find(query, Student.class);
    }

    @Override
    public Mono<Student> updateSubjectsById(String id, String[] subjects) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update().set("subjects", subjects);
        return mongoTemplate.findAndModify(query, update, Student.class);
    }
}
