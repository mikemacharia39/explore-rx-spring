package com.mikehenry.explore_rx_spring.domain.service;

import com.mikehenry.explore_rx_spring.api.dto.StudentRequestDto;
import com.mikehenry.explore_rx_spring.api.dto.StudentResponseDto;
import com.mikehenry.explore_rx_spring.domain.entity.Student;
import com.mikehenry.explore_rx_spring.domain.mapper.StudentMapper;
import com.mikehenry.explore_rx_spring.domain.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public Mono<StudentResponseDto> addStudent(StudentRequestDto studentRequestDto) {
        Student student = studentMapper.toEntity(studentRequestDto);
        return studentRepository.insert(student)
                .map(studentMapper::toResponseDto);
    }


}
