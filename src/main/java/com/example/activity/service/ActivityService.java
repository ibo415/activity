package com.example.activity.service;

import com.example.activity.entity.Activity;
import com.example.activity.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {
    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public Activity save(Activity activity) {
        return activityRepository.save(activity);
    }

    public List<Activity> getAllActivity() {
        return activityRepository.findAll();
    }

    public Activity findById(Long id) {
        return activityRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Activity not found with the id " + id)
        );
    }
}
