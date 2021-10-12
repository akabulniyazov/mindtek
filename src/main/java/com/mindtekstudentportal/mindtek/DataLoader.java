package com.mindtekstudentportal.mindtek;

import com.mindtekstudentportal.mindtek.assignment.Assignment;
import com.mindtekstudentportal.mindtek.student.Student;
import com.mindtekstudentportal.mindtek.subject.Subject;
import com.mindtekstudentportal.mindtek.topics.Topic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataLoader {

    public static List<Student> students;
    public static List<Subject> subjects;
    public static List<Topic> topics;
    public static List<Assignment> assignments;
    public static Integer studentId=118;
    public static Integer subjectId=100;
    public static Integer topicId=12;
    public static Integer assignmentId=16;


    static{
        try {
            students=loadStudents();
            subjects=loadSubjects();
            topics =loadTopics();
            assignments=loadAssignments();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<List<String>> readFile(String path) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line = "";
        List<List<String>> data = new ArrayList<>();

        while((line=br.readLine()) != null) {
            // line ="Southlake,Texas,US,1400";
            // String[] values = {Southlake, Texas, US, 1400};
            String[] values = line.split(",");
            // List<String> list = [Southlake, Texas, US, 1400];
            List<String> list = Arrays.asList(values);
            // data -> list of list -> we adding one value (list) to outer list
            data.add(list);
        }
        return data;
    }



    public static List<Assignment> loadAssignments() throws Exception {
        List<List<String>> assignments = readFile("src/main/resources/data/assignments.csv");
        List<Assignment> assignmentList = new ArrayList<>();

        for(int i=0; i<assignments.size(); i++) {
            String assignmentName = assignments.get(i).get(0);
            int assignmentId = Integer.parseInt(assignments.get(i).get(1));
            int topicId = Integer.parseInt(assignments.get(i).get(2));
            Assignment assignment = new Assignment();
            assignment.setAssignmentName(assignmentName);
            assignment.setAssignmentId(assignmentId);
            assignment.setTopicId(topicId);
            assignmentList.add(assignment);
        }

        return assignmentList;
    }

    public static List<Topic> loadTopics() throws Exception {
        List<List<String>> topics = readFile("src/main/resources/data/topics.csv");
        List<Topic> topicList = new ArrayList<>();
        List<Assignment> assignments = loadAssignments();

        for(int i =0;i<topics.size(); i++) {
            int topicId = Integer.parseInt(topics.get(i).get(2));
            int subjectId = Integer.parseInt(topics.get(i).get(1));
            String topicName = topics.get(i).get(0);
            List<Assignment> topicAssignments = new ArrayList<>();

            for(int a=0;a<assignments.size(); a++) {
                if(assignments.get(a).getTopicId() == Integer.parseInt(topics.get(i).get(2))) {
                    topicAssignments.add(assignments.get(a));
                }
            }

            Topic topic=new Topic();
            topic.setTopicId(topicId);
            topic.setTopicName(topicName);
            topic.setSubjectId(subjectId);
            topic.setAssignments(topicAssignments);
            topicList.add(topic);
        }

        return topicList;

    }

    public static List<Subject> loadSubjects() throws Exception {
        List<List<String>> subjects = readFile("src/main/resources/data/subjects.csv");
        List<Subject> subjectList = new ArrayList<>();
        List<Topic> topics = loadTopics();

        for(int i=0; i<subjects.size(); i++) {
            String subjectName = subjects.get(i).get(0);
            int subjectId = Integer.parseInt(subjects.get(i).get(1));

            List<Topic> subjectTopics = new ArrayList<>();

            for(int a=0;a<topics.size(); a++) {
                if(topics.get(a).getSubjectId() == Integer.parseInt(subjects.get(i).get(1))) {
                    subjectTopics.add(topics.get(a));
                }
            }

            Subject subject=new Subject();
            subject.setSubjectName(subjectName);
            subject.setSubjectId(subjectId);
            subject.setTopics(subjectTopics);
            subjectList.add(subject);
        }

        return subjectList;
    }

    public static List<Student> loadStudents() throws Exception {
        List<List<String>> students= readFile("src/main/resources/data/students.csv");
        List<Student> studentList = new ArrayList<>();
        List<Subject> subjects = loadSubjects();

        for(int a = 0; a<students.size(); a++) {
//			Steven,King,100,90,AD_PRES
            String studentName = students.get(a).get(0);
            int studentId = Integer.parseInt(students.get(a).get(1));

            Student student = new Student();

            student.setFirstName(studentName);
            student.setStudentId(studentId);
            student.setSubjects(subjects);
            studentList.add(student);
        }
        return studentList;

    }

}
