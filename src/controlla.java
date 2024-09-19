package com.glab.controller;

import com.glab.model.Tutorial;
import com.glab.myservices.TutorialServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TutorialController {

    @Autowired
    private TutorialServices tutorialServices;
    @GetMapping("/tutorials")
    public List <Tutorial> getAllTutorials(@RequestParam(required = false) String title){
        return tutorialServices.getAllTutorials();
    }

    @GetMapping("/tutorials/{id}")
    public Optional<Tutorial> getTurialsById(@PathVariable("id") long id){
        return tutorialServices.getTutorialById(id);
    }

    @PostMapping("/tutorials")
    public void createTutorial(@RequestBody Tutorial tutorial){
        tutorialServices.addTutorial(tutorial);
    }

    @PutMapping("/tutorials/{id}")
    public void updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
        Optional<Tutorial> tutorialData = tutorialServices.getTutorialById(id);

        if (tutorialData.isPresent()) {
            Tutorial _tutorial = tutorialData.get();
            _tutorial.setTitle(tutorial.getTitle());
            _tutorial.setDescription(tutorial.getDescription());
            _tutorial.setPublished(tutorial.isPublished());
            tutorialServices.addTutorial(_tutorial);
        }
    }
    @DeleteMapping("/tutorials/{id}")
    public void deleteTutorial(@PathVariable("id") long id) {
        tutorialServices.deleteTutorial(id);
    }

    @DeleteMapping("/tutorials")
    public void deleteAllTutorials() {
        tutorialServices.deleteAllTutorials();
    }
    @GetMapping("/tutorials/published")
    public ResponseEntity<List<Tutorial>> findByPublished() {
        return tutorialServices.findByPublished();   }

}