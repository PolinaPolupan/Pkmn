package example.pokemon.service;

import example.pokemon.dto.StudentDto;
import example.pokemon.exception.DuplicateStudentException;
import example.pokemon.exception.StudentNotFoundException;
import example.pokemon.mapper.StudentMapper;
import example.pokemon.model.Student;
import example.pokemon.dto.GetStudentRequest;
import example.pokemon.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository repository;
    private final StudentMapper mapper;

    public void save(StudentDto student) {
        // Check if a student already exists with the same firstName and lastName
        Optional<Student> existingStudent = repository.findByFirstNameAndLastName(
                student.getFirstName(),
                student.getLastName()
        );

        existingStudent.ifPresent(s -> {
            throw new DuplicateStudentException("A student with the same name already exists.");
        });

        repository.save(mapper.mapDtoToStudent(student));
    }

    public List<StudentDto> getAll(Pageable page) {
        return repository.findAll(page)
                .stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    public List<StudentDto> getByStudentGroup(String group) {
        return repository.findByStudentGroup(group)
                .stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    public StudentDto getByFirstNameLastName(GetStudentRequest request) {

        Student student = repository.findByFirstNameAndLastName(
            request.getFirstName(),
            request.getLastName())
            .orElseThrow(() -> { throw new StudentNotFoundException("Student not found"); }
        );

        return mapper.mapToDto(student);
    }
}
