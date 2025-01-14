package com.mikehenry.explore_rx_spring.api.controller;

import com.mikehenry.explore_rx_spring.AbstractBaseIntegrationTest;
import com.mikehenry.explore_rx_spring.api.dto.AddressDto;
import com.mikehenry.explore_rx_spring.api.dto.StudentRequestDto;
import com.mikehenry.explore_rx_spring.api.dto.StudentResponseDto;
import com.mikehenry.explore_rx_spring.domain.enumeration.LearningScheduleType;
import io.github.serpro69.kfaker.Faker;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class StudentControllerTest extends AbstractBaseIntegrationTest {
    
    @Autowired
    private WebTestClient webTestClient;
    
    @Autowired
    private Faker faker;
    
    @Test
    void testAddStudent() {
        StudentRequestDto studentDto = StudentRequestDto.builder()
                .firstName(faker.getName().firstName())
                .lastName(faker.getName().lastName())
                .email(faker.getInternet().email())
                .address(AddressDto.builder()
                        .country(faker.getAddress().country())
                        .city(faker.getAddress().city())
                        .addressLine1(faker.getAddress().streetAddress())
                        .postalBox(faker.getAddress().mailbox())
                        .postalBox("83837")
                        .build())
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .scheduleType(LearningScheduleType.FULL_TIME)
                .subjects(List.of("Biology", "English"))
                .totalSpentInBooks(BigDecimal.valueOf(200))
                .build();
        
        webTestClient.post().uri("/students")
                .bodyValue(studentDto)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(StudentResponseDto.class)
                .consumeWith(response -> {
                    Assertions.assertThat(response.getResponseBody()).isNotNull();

                    StudentResponseDto responseBody = response.getResponseBody();
                    Assertions.assertThat(responseBody.getId()).isNotNull().isNotBlank();
                });
    }
}
