package com.mindtekstudentportal.mindtek.subject;

import com.mindtekstudentportal.mindtek.DataLoader;
import com.mindtekstudentportal.mindtek.topics.Topic;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SubjectController {

    @GetMapping("/subjects")
    public List<Subject> getSubjects(@RequestParam(value = "limit",defaultValue = "5") int limit,
                                     @RequestParam(value = "order",defaultValue = "asc") String order) throws Exception {
        List<Subject> subjects = DataLoader.subjects;
        if(order.equalsIgnoreCase("asc"))
            subjects.sort(new Subject());
        else
            subjects.sort(Collections.reverseOrder(new Subject()));
        return subjects.stream().limit(limit).collect(Collectors.toList());
    }

    @GetMapping("/subjects/{id}")
    public ResponseEntity<Subject> getSubjects(@PathVariable String id) throws Exception {
        for(Subject s:DataLoader.subjects){
            if(s.getSubjectId().toString().equals(id)){
                return new ResponseEntity<Subject>(s,HttpStatus.OK);
            }
        }
        return new ResponseEntity<Subject>(HttpStatus.NOT_FOUND);
    }


    @PostMapping(path="/subjects", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Subject> postSubject(@RequestBody Subject subject) throws Exception {
        subject.setSubjectId(DataLoader.subjectId+=1);
        DataLoader.subjects.add(subject);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(subject.getSubjectId()).toUri();
        return ResponseEntity.created(uri).body(subject);
    }

    @PutMapping("subjects/{id}")
    public ResponseEntity<Subject> putSubject(@PathVariable Integer id, @RequestBody Subject subject) throws Exception {
        subject.setSubjectId(id);
        for (int i=0; i<DataLoader.subjects.size(); i++){
            if(DataLoader.subjects.get(i).getSubjectId().toString().equals(id.toString())){
                DataLoader.subjects.set(i,subject);
            }
        }
        return new ResponseEntity<Subject>(subject, HttpStatus.OK);
    }

    @DeleteMapping("/subjects/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Integer id){
        for(int i =0; i<DataLoader.subjects.size(); i++){
            if(id.equals(DataLoader.subjects.get(i).getSubjectId())){
                DataLoader.subjects.remove(i);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }


}
