package se.verran.demointegrationtest.controllers;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;
import se.verran.demointegrationtest.entities.Student;
import se.verran.demointegrationtest.repositories.StudentRepository;
import se.verran.demointegrationtest.services.StudentService;


import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class StudentControllerStudentServiceStudentRepositoryIntegrationTest {

    private StudentController studentController;;
    private StudentService studentService;
    private StudentRepository studentRepository;

    @Autowired
    public StudentControllerStudentServiceStudentRepositoryIntegrationTest(StudentController studentController, StudentService studentService, StudentRepository studentRepository) {
        this.studentController = studentController;
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }

    @Test
    void addStudentShouldReturnStatusCode200AndStudentBody() {
        // Given
        Student student = new Student("Lars", "Skoog", LocalDate.of(51,5,9), "lars@skoog.se");

        // When
        ResponseEntity<Student> response = studentController.addStudent(student);
        // Then
        assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.OK)).isTrue();
        assertThat(response.getBody()).isEqualTo(student);
    }
    @Test
    void addStudentShouldReturnStatusCodeConflict() {
        // Given
        Student student = new Student("Lars", "Skoog", LocalDate.of(51,5,9), "lars@skoog.se");
        Student firstAddedStudent = new Student("Lars", "Skog", LocalDate.of(69,2,9), "lars@skoog.se");
        // When
        studentRepository.save(firstAddedStudent);
        ResponseStatusException result = assertThrows(ResponseStatusException.class, () -> studentController.addStudent(student));
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(result.getBody().getDetail()).isEqualTo("Email lars@skoog.se already exists");
    }

    @Test
    void getStudentById() {
    }

    @Test
    void getAllStudents() {
    }

    @Test
    void updateStudentById() {
    }

    @Test
    void setGradeForStudentById() {
    }

    @Test
    void deleteStudentById() {
    }
}