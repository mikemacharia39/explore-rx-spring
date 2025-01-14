package com.mikehenry.explore_rx_spring.api.controller;

import com.mikehenry.explore_rx_spring.api.dto.StudentRequestDto;
import com.mikehenry.explore_rx_spring.api.dto.StudentResponseDto;
import com.mikehenry.explore_rx_spring.api.dto.StudentSearchParams;
import com.mikehenry.explore_rx_spring.domain.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@Validated
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<StudentResponseDto> addStudent(@Valid @RequestBody StudentRequestDto studentRequestDto) {
        return studentService.addStudent(studentRequestDto);
    }

    @PutMapping("/{id}")
    public Mono<StudentResponseDto> updateStudent(@PathVariable String id,
                                                  @Valid @RequestBody StudentRequestDto studentRequestDto) {
        return studentService.updateStudent(id, studentRequestDto);
    }

    @PutMapping("/{id}/subjects")
    public Mono<StudentResponseDto> updateSubjectsById(@PathVariable String id, @RequestBody Map<String, List<String>> subjectData) {
        return studentService.updateStudentSubjects(id, subjectData);
    }

    @GetMapping
    public Flux<StudentResponseDto> searchStudents(@SpringQueryMap StudentSearchParams studentSearchParams) {
        return studentService.searchStudents(studentSearchParams);
    }

    @GetMapping("/{id}")
    public Mono<StudentResponseDto> getStudentById(@PathVariable String id) {
        return studentService.getStudentById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteStudent(@PathVariable String id) {
        return studentService.deleteStudent(id);
    }
}
