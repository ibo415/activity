package com.example.activity.controller;

import com.example.activity.entity.Activity;
import com.example.activity.entity.Participant;
import com.example.activity.service.ActivityService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/activities")
@CrossOrigin(origins = "http://localhost:5173")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }


    @PostMapping("/activity")
    public Activity createActivity(@RequestBody Activity activity){
        return activityService.save(activity);
    }

    @GetMapping
    public List<Activity> getAllActivity(){
        return activityService.getAllActivity();
    }

    @GetMapping("/{id}")
    public Activity getActivityById(@PathVariable Long id){
        return  activityService.findById(id);
    }

    @PostMapping("/{id}/participants")
    public Participant addParticipant(@PathVariable Long id) {
        // Benutzernamen des aktuell authentifizierten Benutzers abrufen
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Gibt den Benutzernamen zurück

        // Teilnehmer hinzufügen
        return activityService.addParticipant(id, username);
    }

    @GetMapping("/{id}/participants")
    public List<Participant> getParticipants(@PathVariable Long id){
        return activityService.getParticipantsForActivity(id);
    }
}
