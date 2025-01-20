package com.example.activity.controller;

import com.example.activity.entity.Activity;
import com.example.activity.repository.ActivityRepository;
import com.example.activity.service.ActivityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/activities")
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
}
