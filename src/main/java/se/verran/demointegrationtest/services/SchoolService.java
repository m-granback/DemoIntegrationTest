package se.verran.demointegrationtest.services;


import se.verran.demointegrationtest.entities.Student;

import java.util.List;

public interface SchoolService {
    String numberOfStudentsPerGroupWhenDivideIntoNumberOfGroups(int numberOfGroups);
    String numberOfGroupsWhenDividedIntoGroupsOf(int studentsPerGroup);
    String calculateAverageGrade();
    List<Student> getTopScoringStudents();
}
