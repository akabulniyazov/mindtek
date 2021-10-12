package com.mindtekstudentportal.mindtek.topics;

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
public class TopicController {


    @GetMapping("/topics")
    public List<Topic> getTopics() throws Exception {
        return DataLoader.topics;
    }

    @GetMapping("/topics/{id}")
    public ResponseEntity<Topic> getTopics(@PathVariable String id) throws Exception {
        for(Topic s:DataLoader.topics){
            if(s.getTopicId().toString().equals(id)){
                return new ResponseEntity<Topic>(s,HttpStatus.OK);
            }
        }
        return new ResponseEntity<Topic>(HttpStatus.NOT_FOUND);
    }


    @PostMapping(path="/topics", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Topic> postTopic(@RequestBody Topic topic) throws Exception {
        topic.setTopicId(DataLoader.topicId+=1);
        DataLoader.topics.add(topic);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(topic.getTopicId()).toUri();
        return ResponseEntity.created(uri).body(topic);
    }

    @PutMapping("topics/{id}")
    public ResponseEntity<Topic> putTopic(@PathVariable Integer id, @RequestBody Topic topic) throws Exception {
        topic.setTopicId(id);
        for (int i=0; i<DataLoader.topics.size(); i++){
            if(DataLoader.topics.get(i).getTopicId().toString().equals(id.toString())){
                DataLoader.topics.set(i,topic);
            }
        }
        return new ResponseEntity<Topic>(topic, HttpStatus.OK);
    }

    @DeleteMapping("/topics/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Integer id){
        for(int i =0; i<DataLoader.topics.size(); i++){
            if(id.equals(DataLoader.topics.get(i).getTopicId())){
                DataLoader.topics.remove(i);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }



}
