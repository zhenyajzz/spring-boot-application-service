package web.app.springbootapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.app.springbootapplication.entity.Tutorial;
import web.app.springbootapplication.repository.TutorialRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TutorialController {

    @Autowired
    TutorialRepository tutorialRepository;

    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
        List<Tutorial> tutorials = new ArrayList<>();

        if (title == null)
            tutorialRepository.findAll().forEach(tutorials::add);
        else
            tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);

        if (tutorials.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(tutorials, HttpStatus.OK);
    }

    @GetMapping("/tutorials/published/{published}")
    public ResponseEntity<List<Tutorial>> getTutorialByPublished(@PathVariable boolean published) {

        List<Tutorial> getTutorialsByPublished = tutorialRepository.findByPublished(published);

        if (getTutorialsByPublished.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(getTutorialsByPublished, HttpStatus.OK);
    }

    @GetMapping("/tutorials/description")
    public ResponseEntity<Tutorial> getTutorialByDescription(@RequestParam String description){

        Tutorial getTutorialByDescription = tutorialRepository.findByDescription(description);

        if (getTutorialByDescription == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(getTutorialByDescription,HttpStatus.OK);
    }

    @GetMapping("/tutorialJpql")
    public ResponseEntity<List<Tutorial>> getAllTutorialsByJpql(){

        List<Tutorial> getAllTutorialsByJpql = tutorialRepository.findAllFromTutorial();

        if (getAllTutorialsByJpql.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(getAllTutorialsByJpql,HttpStatus.OK);
    }

    @GetMapping("/tutorial/published/{published}")
    public ResponseEntity<List<Tutorial>> getTutorialByPublishedQuery(@PathVariable boolean published){

        List<Tutorial> getTutorialByPublishedQuery = tutorialRepository.findTutorialByPublished(published);

        if (getTutorialByPublishedQuery.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(getTutorialByPublishedQuery,HttpStatus.OK);
    }
}
