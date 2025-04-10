package se.verran.demointegrationtest.controllers;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import se.verran.demointegrationtest.entities.Student;
import se.verran.demointegrationtest.repositories.StudentRepository;
import se.verran.demointegrationtest.services.StudentService;


import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
// Vi behöver utföra rollback så att vi har samma utgångsläge i varje testfall
@Transactional
@Rollback
class StudentControllerStudentServiceStudentRepositoryIntegrationTest {

    private StudentController studentController;;
    private StudentRepository studentRepository;

    @Autowired
    public StudentControllerStudentServiceStudentRepositoryIntegrationTest(StudentController studentController, StudentService studentService, StudentRepository studentRepository) {
        this.studentController = studentController;
        this.studentRepository = studentRepository;
    }

    @Test
    void addStudentShouldReturnStatusCode200AndStudentBody() {
        System.out.println("Antal studerande: " + studentRepository.findAll().size());

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
        System.out.println("Antal studerande: " + studentRepository.findAll().size());
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