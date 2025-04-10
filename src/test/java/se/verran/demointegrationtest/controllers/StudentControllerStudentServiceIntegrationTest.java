package se.verran.demointegrationtest.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import se.verran.demointegrationtest.entities.Student;
import se.verran.demointegrationtest.repositories.StudentRepository;
import se.verran.demointegrationtest.services.StudentServiceImpl;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentControllerStudentServiceIntegrationTest {

    @Mock
    private StudentRepository studentRepositoryMock;

    @InjectMocks
    private StudentServiceImpl studentService;

    private StudentController studentController;

    @BeforeEach
    public void setup(){
         studentController= new StudentController(studentService);
    }

    @Test
    public void addStudentShouldReturnStatusCode200AndStudentBody() {
        // Given
        Student student = new Student("Lars", "Johansson", LocalDate.of(1943,6,28), "lars@oldtimers.com");
        when(studentRepositoryMock.save(student)).thenReturn(student);
        // When
        ResponseEntity<Student> response = studentController.addStudent(student);
        // Then
        assertTrue(response.getStatusCode().isSameCodeAs(HttpStatus.OK));
        assertThat(response.getBody()).isEqualTo(student);
    }

    @Test
    void addStudentShouldReturnStatusCodeConflict() {
        // Given
        Student student = new Student("Lars", "Skoog", LocalDate.of(51,5,9), "lars@skoog.se");
        doThrow(new ResponseStatusException(HttpStatus.CONFLICT, "Email lars@skoog.se already exists")).when(studentRepositoryMock).save(student);

        // When
        ResponseStatusException result = assertThrows(ResponseStatusException.class, () -> studentController.addStudent(student));
        // Then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(result.getBody().getDetail()).isEqualTo("Email lars@skoog.se already exists");
    }

}
