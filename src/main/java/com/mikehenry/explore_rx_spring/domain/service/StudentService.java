package com.mikehenry.explore_rx_spring.domain.service;

import com.mikehenry.explore_rx_spring.api.dto.StudentRequestDto;
import com.mikehenry.explore_rx_spring.api.dto.StudentResponseDto;
import com.mikehenry.explore_rx_spring.api.dto.StudentSearchParams;
import com.mikehenry.explore_rx_spring.domain.entity.Student;
import com.mikehenry.explore_rx_spring.domain.mapper.StudentMapper;
import com.mikehenry.explore_rx_spring.domain.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    private static final String STUDENT_NOT_FOUND_MESSAGE = "Student with id %s not found";

    public Mono<StudentResponseDto> addStudent(StudentRequestDto studentRequestDto) {
        Student student = studentMapper.toEntity(studentRequestDto);
        return studentRepository.insert(student)
                .map(studentMapper::toResponseDto);
    }


    public Mono<StudentResponseDto> getStudentById(String id) {
        return studentRepository.existsById(id)
                .flatMap(exists -> {
                    if (Boolean.TRUE.equals(exists)) {
                        return studentRepository.findById(id).map(studentMapper::toResponseDto);
                    } else {
                        return Mono.error(
                                new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(STUDENT_NOT_FOUND_MESSAGE, id))
                        );
                    }
                });

    }

    public Mono<StudentResponseDto> updateStudent(String id, StudentRequestDto studentRequestDto) {
        return studentRepository.existsById(id)
                .flatMap(exists -> {
                    if (Boolean.TRUE.equals(exists)) {
                        Student student = studentMapper.toEntity(studentRequestDto);
                        student.setId(id);
                        return studentRepository.save(student).map(studentMapper::toResponseDto);
                    } else {
                        return Mono.error(
                                new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(STUDENT_NOT_FOUND_MESSAGE, id))
                        );
                    }
                });
    }

    public Mono<StudentResponseDto> updateStudentSubjects(String id, Map<String, List<String>> subjectData) {
        List<String> subjects = subjectData.get("subjects");
        return studentRepository.existsById(id)
                .flatMap(exists -> {
                    if (exists == Boolean.TRUE) {
                        return studentRepository.updateSubjectsById(id, subjects.toArray(new String[0]))
                                .map(studentMapper::toResponseDto);
                    } else {
                        return Mono.error(
                                new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(STUDENT_NOT_FOUND_MESSAGE, id))
                        );
                    }
                });
    }

    public Flux<StudentResponseDto> searchStudents(StudentSearchParams searchParams) {
        return studentRepository.searchStudents(searchParams)
                .map(studentMapper::toResponseDto);
    }

    public Mono<Void> deleteStudent(String id) {
        return studentRepository.existsById(id)
                .flatMap(exists -> {
                    if (Boolean.TRUE.equals(exists)) {
                        return studentRepository.deleteById(id);
                    } else {
                        return Mono.error(
                                new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(STUDENT_NOT_FOUND_MESSAGE, id))
                        );
                    }
                });
    }
}
