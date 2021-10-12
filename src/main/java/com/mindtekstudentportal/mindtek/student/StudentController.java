package com.mindtekstudentportal.mindtek.student;

import com.mindtekstudentportal.mindtek.DataLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class StudentController {

    @GetMapping("/students")
    public List<Student> getStudents(@RequestParam(value = "studentId",defaultValue = "-1") int studentId) throws Exception {
        if(studentId==-1)return DataLoader.students;
        else return DataLoader.students.stream().filter(s->s.getStudentId()==studentId).collect(Collectors.toList());
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudents(@PathVariable String id) throws Exception {
        for(Student s:DataLoader.students){
            if(s.getStudentId().toString().equals(id)){
                return new ResponseEntity<Student>(s,HttpStatus.OK);
            }
        }
        return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
    }


    @PostMapping(path="/students", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Student> postStudent(@RequestBody Student student) throws Exception {
        student.setStudentId(DataLoader.studentId+=1);
        DataLoader.students.add(student);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(student.getStudentId()).toUri();
        return ResponseEntity.created(uri).body(student);
    }

    @PutMapping("students/{id}")
    public ResponseEntity<Student> putStudent(@PathVariable Integer id, @RequestBody Student student) throws Exception {
        student.setStudentId(id);
        for (int i=0; i<DataLoader.students.size(); i++){
            if(DataLoader.students.get(i).getStudentId().toString().equals(id.toString())){
                DataLoader.students.set(i,student);
            }
        }
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id){
        for(int i =0; i<DataLoader.students.size(); i++){
            if(id.equals(DataLoader.students.get(i).getStudentId())){
                DataLoader.students.remove(i);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

}
