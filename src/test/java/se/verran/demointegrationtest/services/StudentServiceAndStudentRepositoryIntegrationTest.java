package se.verran.demointegrationtest.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;
import se.verran.demointegrationtest.entities.Student;
import se.verran.demointegrationtest.repositories.StudentRepository;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentServiceAndStudentRepositoryIntegrationTest {

    private StudentService studentService;
    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceAndStudentRepositoryIntegrationTest(StudentService studentService, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }

    @Test
    void addStudent() {

    }

    @Test
    void getAllStudents() {
    }

    @Test
    void deleteStudent() {
    }

    @Test
    void updateStudent() {
    }

    @Test
    void getStudentByIdShouldReturnStudent() {
        // Given
        Student student = new Student("Lars", "Skoog", LocalDate.of(51,5,9),"lars@skoog.se");
        // When
        Student savedStudent = studentRepository.save(student);
        Student foundStudent = studentService.getStudentById(savedStudent.getId());
        // Then
        assertThat(foundStudent.getEmail()).isEqualTo(student.getEmail());
    }
    @Test
    void getStudentByIdShouldThrowException(){
        // Given
        int id = 1;
        // When Then
        assertThrows(ResponseStatusException.class, ()->studentService.getStudentById(id));
    }

    @Test
    void setGradeForStudentById() {
    }
}