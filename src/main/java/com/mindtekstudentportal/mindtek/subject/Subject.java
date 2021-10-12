package com.mindtekstudentportal.mindtek.subject;

import com.mindtekstudentportal.mindtek.topics.Topic;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Subject implements Comparator<Subject> {

    private String subjectName;
    private Integer subjectId;
    private List<Topic> topics;

    @Override
    public int compare(Subject customer1, Subject customer2){
        return customer1.getSubjectName().compareTo(customer2.getSubjectName());
    }

}
