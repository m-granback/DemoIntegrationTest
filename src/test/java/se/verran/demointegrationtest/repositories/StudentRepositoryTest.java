package se.verran.demointegrationtest.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.verran.demointegrationtest.entities.Student;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    private static Student student;

    @BeforeEach
    void beforeEach() {
        student = new Student("Lars", "Skoog", LocalDate.of(83,1,29),"lars@aol.com");
    }

    @Test
    void existsStudentByEmailShouldReturnTrueTest() {
        // Given
        studentRepository.save(student);
        System.out.println("Antal studerande" + studentRepository.findAll().size());
        // When
        boolean studentFound = studentRepository.existsStudentByEmail(student.getEmail());
        // Then
        assertThat(studentFound).isTrue();
    }
    @Test
    void existsStudentByEmailShouldReturnFalseTest() {
        // when
        boolean studentFound = studentRepository.existsStudentByEmail("micke@aol.com");
        // then
        assertThat(studentFound).isFalse();
    }
}