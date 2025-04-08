package se.verran.demointegrationtest.services;


import se.verran.demointegrationtest.entities.Student;

import java.util.List;

public interface StudentService {
    Student addStudent(Student student);
    List<Student> getAllStudents();
    void deleteStudent(int id);
    Student updateStudent(Student student);
    Student getStudentById(int id);
    Student setGradeForStudentById(int studentId, String gradeAsString);


}
