package web.app.springbootapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.app.springbootapplication.entity.Tutorial;
import web.app.springbootapplication.repository.TutorialRepository;
import web.app.springbootapplication.service.TutorialService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TutorialController {


    @Autowired
    TutorialRepository tutorialRepository;


    @Autowired
    TutorialService tutorialService;

    @GetMapping("/tutorials/0")
    public ResponseEntity<List<Tutorial>> getAllTutorialsWithPageable(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {

        List<Tutorial> getAllTutorialsWithPageable = tutorialService.getAllTutorials(pageNo, pageSize, sortBy);

        return new ResponseEntity<>(getAllTutorialsWithPageable, HttpStatus.OK);
    }

    @GetMapping("/tutorials/published/page")
    public ResponseEntity<Map<String, Object>> findByPublished(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "3") Integer pageSize) {

        try {
            List<Tutorial> tutorials = new ArrayList<>();
            Pageable paging = PageRequest.of(pageNo, pageSize);

            Page<Tutorial> pageTuts = tutorialRepository.findByPublished(true, paging);
            tutorials = pageTuts.getContent();

            Map<String, Object> response = new HashMap<>();

            response.put("tutorials", tutorials);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tutorials/pageable")
    public ResponseEntity<Map<String, Object>> findAllTutorialsByPageable(
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "3") Integer pageSize) {

        try {
            List<Tutorial> tutorialList = new ArrayList<>();
            Pageable paging = PageRequest.of(pageNo, pageSize);

            Page<Tutorial> pageTut;
            if (title == null)
                pageTut = tutorialRepository.findAll(paging);

            else pageTut = tutorialRepository.findByTitleContaining(title, paging);
            tutorialList = pageTut.getContent();

            Map<String, Object> response = new HashMap<>();

            response.put("tutorial", tutorialList);
            response.put("currentPage", pageTut.getNumber());
            response.put("totalItems", pageTut.getTotalElements());
            response.put("totalPages", pageTut.getTotalPages());

            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tutorials/1")
    public ResponseEntity<List<Tutorial>> getAllTutorialsWithPageable1(
            @RequestParam(defaultValue = "1") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {

        List<Tutorial> getAllTutorialsWithPageable = tutorialService.getAllTutorials(pageNo, pageSize, sortBy);

        return new ResponseEntity<>(getAllTutorialsWithPageable, HttpStatus.OK);
    }


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
    public ResponseEntity<Tutorial> getTutorialByDescription(@RequestParam String description) {

        Tutorial getTutorialByDescription = tutorialRepository.findByDescription(description);

        if (getTutorialByDescription == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(getTutorialByDescription, HttpStatus.OK);
    }

    @GetMapping("/tutorialJpql")
    public ResponseEntity<List<Tutorial>> getAllTutorialsByJpql() {

        List<Tutorial> getAllTutorialsByJpql = tutorialRepository.findAllFromTutorial();

        if (getAllTutorialsByJpql.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(getAllTutorialsByJpql, HttpStatus.OK);
    }

    @GetMapping("/tutorial/published/{published}")
    public ResponseEntity<List<Tutorial>> getTutorialByPublishedQuery(@PathVariable boolean published) {

        List<Tutorial> getTutorialByPublishedQuery = tutorialRepository.findTutorialByPublished(published);

        if (getTutorialByPublishedQuery.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(getTutorialByPublishedQuery, HttpStatus.OK);
    }
}
