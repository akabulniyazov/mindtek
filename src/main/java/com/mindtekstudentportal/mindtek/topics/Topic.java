package com.mindtekstudentportal.mindtek.topics;

import com.mindtekstudentportal.mindtek.assignment.Assignment;
import com.mindtekstudentportal.mindtek.subject.Subject;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Topic {

    private String topicName;
    private Integer topicId;
    private Integer subjectId;
    private List<Assignment> assignments;


}
