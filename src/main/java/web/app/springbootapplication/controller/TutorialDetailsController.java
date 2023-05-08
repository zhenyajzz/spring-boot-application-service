package web.app.springbootapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.app.springbootapplication.entity.Tutorial;
import web.app.springbootapplication.entity.TutorialDetails;
import web.app.springbootapplication.exception.ResourceNotFoundException;
import web.app.springbootapplication.repository.TutorialDetailsRepository;
import web.app.springbootapplication.repository.TutorialRepository;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TutorialDetailsController {

    @Autowired
    TutorialRepository tutorialRepository;

    @Autowired
    TutorialDetailsRepository tutorialDetailsRepository;


    @GetMapping({ "/details/{id}", "/tutorials/{id}/details" })
    public ResponseEntity<TutorialDetails> getDetailsById(@PathVariable(value = "id") Long id){

        TutorialDetails details = tutorialDetailsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial Details with id = " + id));

        return new ResponseEntity<>(details,HttpStatus.OK);
    }

    @PostMapping("/tutorials/{tutorialId}/details")
    public ResponseEntity<TutorialDetails> createDetails(@PathVariable Long tutorialId,
                                                         @RequestBody TutorialDetails detailsRequest){
        Tutorial findTutorialId = tutorialRepository.findById(tutorialId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId));

        detailsRequest.setCreatedOn(new Date());
        detailsRequest.setTutorial(findTutorialId);

        TutorialDetails details = tutorialDetailsRepository.save(detailsRequest);

        return new ResponseEntity<>(details, HttpStatus.CREATED);

    }
}
