package com.mindtekstudentportal.mindtek.assignment;


import com.mindtekstudentportal.mindtek.DataLoader;
import com.mindtekstudentportal.mindtek.student.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AssignmentController {



    @GetMapping("/assignments")
    public List<Assignment> getAssignments() throws Exception {
        return DataLoader.assignments;
    }

    @GetMapping("/assignments/{id}")
    public ResponseEntity<Assignment> getAssignments(@PathVariable String id) throws Exception {
        for(Assignment s:DataLoader.assignments){
            if(s.getAssignmentId().toString().equals(id)){
                return new ResponseEntity<Assignment>(s,HttpStatus.OK);
            }
        }
        return new ResponseEntity<Assignment>(HttpStatus.OK);
    }


    @PostMapping(path="/assignments", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Assignment> postAssignment(@RequestBody Assignment assignment) throws Exception {
        assignment.setAssignmentId(DataLoader.assignmentId+=1);
        DataLoader.assignments.add(assignment);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(assignment.getAssignmentId()).toUri();
        return ResponseEntity.created(uri).body(assignment);
    }

    @PutMapping("assignments/{id}")
    public ResponseEntity<Assignment> putAssignment(@PathVariable Integer id, @RequestBody Assignment assignment) throws Exception {
        assignment.setAssignmentId(id);
        for (int i=0; i<DataLoader.assignments.size(); i++){
            if(DataLoader.assignments.get(i).getAssignmentId().toString().equals(id.toString())){
                DataLoader.assignments.set(i,assignment);
            }
        }
        return new ResponseEntity<Assignment>(assignment, HttpStatus.OK);
    }

    @DeleteMapping("/assignments/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable Integer id){
        for(int i =0; i<DataLoader.assignments.size(); i++){
            if(id.equals(DataLoader.assignments.get(i).getAssignmentId())){
                DataLoader.assignments.remove(i);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }


}
