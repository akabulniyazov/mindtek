package com.mindtekstudentportal.mindtek.student;

import com.mindtekstudentportal.mindtek.assignment.Assignment;
import com.mindtekstudentportal.mindtek.grade.Grade;
import com.mindtekstudentportal.mindtek.subject.Subject;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {

    private String firstName;
    private Integer studentId;
    private List<Subject> subjects;

}
