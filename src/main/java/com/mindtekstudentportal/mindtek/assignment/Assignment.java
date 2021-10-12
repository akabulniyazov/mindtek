package com.mindtekstudentportal.mindtek.assignment;

import com.mindtekstudentportal.mindtek.grade.Grade;
import com.mindtekstudentportal.mindtek.student.Student;
import java.time.LocalDateTime;
import java.util.Map;

import com.mindtekstudentportal.mindtek.topics.Topic;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Assignment {

    private String assignmentName;
    private Integer assignmentId;
    private Integer topicId;

}
