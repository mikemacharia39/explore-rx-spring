package com.mikehenry.explore_rx_spring.api.controller;

import com.mikehenry.explore_rx_spring.AbstractBaseIntegrationTest;
import com.mikehenry.explore_rx_spring.api.dto.AddressDto;
import com.mikehenry.explore_rx_spring.api.dto.StudentRequestDto;
import com.mikehenry.explore_rx_spring.api.dto.StudentResponseDto;
import com.mikehenry.explore_rx_spring.domain.enumeration.Gender;
import com.mikehenry.explore_rx_spring.domain.enumeration.LearningScheduleType;
import io.github.serpro69.kfaker.Faker;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
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

    private static Faker faker;

    @BeforeAll
    static void setUp() {
        faker = new Faker();
    }

    @Test
    void testAddStudent() {
        StudentRequestDto studentDto = mockStudentRequest(faker.getInternet().email());
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

    @Test
    void testAddStudentWithExistingEmail() {
        StudentRequestDto studentDto = mockStudentRequest(faker.getInternet().email());
        StudentResponseDto studentResponseDto = callCreateStudent(studentDto);
        Assertions.assertThat(studentResponseDto).isNotNull();

        webTestClient.post().uri("/students")
                .bodyValue(studentDto)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody()
                .jsonPath("$.status").isEqualTo("409")
                .jsonPath("$.error").isEqualTo("Conflict");
    }

    @Test
    void testGetStudentById() {
        StudentRequestDto studentDto = mockStudentRequest(faker.getInternet().email());
        StudentResponseDto studentResponseDto = callCreateStudent(studentDto);

        Assertions.assertThat(studentResponseDto).isNotNull();
        Assertions.assertThat(studentResponseDto.getId()).isNotNull().isNotBlank();

        webTestClient.get().uri("/students/{id}", studentResponseDto.getId())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(StudentResponseDto.class)
                .consumeWith(response -> {
                    Assertions.assertThat(response.getResponseBody()).isNotNull();

                    StudentResponseDto responseBody = response.getResponseBody();
                    Assertions.assertThat(responseBody.getId()).isEqualTo(studentResponseDto.getId());
                    Assertions.assertThat(responseBody.getFirstName()).isEqualTo(studentDto.getFirstName());
                    Assertions.assertThat(responseBody.getLastName()).isEqualTo(studentDto.getLastName());
                    Assertions.assertThat(responseBody.getEmail()).isEqualTo(studentDto.getEmail());
                    Assertions.assertThat(responseBody.getDateOfBirth()).isEqualTo(studentDto.getDateOfBirth());
                    Assertions.assertThat(responseBody.getScheduleType()).isEqualTo(studentDto.getScheduleType());
                    Assertions.assertThat(responseBody.getSubjects()).isEqualTo(studentDto.getSubjects());
                    Assertions.assertThat(responseBody.getTotalSpentInBooks()).isEqualTo(studentDto.getTotalSpentInBooks());
                    Assertions.assertThat(responseBody.getGender()).isEqualTo(studentDto.getGender());
                });
    }

    @Test
    void testUpdateStudent() {
        StudentRequestDto studentDto = mockStudentRequest(faker.getInternet().email());
        StudentResponseDto studentResponseDto = callCreateStudent(studentDto);

        Assertions.assertThat(studentResponseDto).isNotNull();
        Assertions.assertThat(studentResponseDto.getId()).isNotNull().isNotBlank();

        StudentRequestDto updatedStudentDto = mockStudentRequest("newEmail@mail.com");
        webTestClient.put().uri("/students/{id}", studentResponseDto.getId())
                .bodyValue(updatedStudentDto)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(StudentResponseDto.class)
                .consumeWith(response -> {
                    Assertions.assertThat(response.getResponseBody()).isNotNull();

                    StudentResponseDto responseBody = response.getResponseBody();
                    Assertions.assertThat(responseBody.getId()).isEqualTo(studentResponseDto.getId());
                    Assertions.assertThat(responseBody.getFirstName()).isEqualTo(updatedStudentDto.getFirstName());
                    Assertions.assertThat(responseBody.getLastName()).isEqualTo(updatedStudentDto.getLastName());
                    Assertions.assertThat(responseBody.getEmail()).isEqualTo(updatedStudentDto.getEmail());
                    Assertions.assertThat(responseBody.getDateOfBirth()).isEqualTo(updatedStudentDto.getDateOfBirth());
                    Assertions.assertThat(responseBody.getScheduleType()).isEqualTo(updatedStudentDto.getScheduleType());
                    Assertions.assertThat(responseBody.getSubjects()).isEqualTo(updatedStudentDto.getSubjects());
                    Assertions.assertThat(responseBody.getTotalSpentInBooks()).isEqualTo(updatedStudentDto.getTotalSpentInBooks());
                    Assertions.assertThat(responseBody.getGender()).isEqualTo(updatedStudentDto.getGender());
                });
    }

    @Test
    void testDeleteStudent() {
        StudentRequestDto studentDto = mockStudentRequest(faker.getInternet().email());
        StudentResponseDto studentResponseDto = callCreateStudent(studentDto);

        webTestClient.delete().uri("/students/{id}", studentResponseDto.getId())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNoContent();
    }

    private StudentResponseDto callCreateStudent(StudentRequestDto studentRequestDto) {
        return webTestClient.post().uri("/students")
                .bodyValue(studentRequestDto)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(StudentResponseDto.class)
                .returnResult()
                .getResponseBody();
    }

    private static StudentRequestDto mockStudentRequest(final String email) {
        return StudentRequestDto.builder()
                .firstName(faker.getName().firstName())
                .lastName(faker.getName().lastName())
                .email(email)
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
                .gender(Gender.MALE)
                .build();
    }
}
