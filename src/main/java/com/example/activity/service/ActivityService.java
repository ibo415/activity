package com.example.activity.service;

import com.example.activity.entity.Activity;
import com.example.activity.entity.Participant;
import com.example.activity.repository.ActivityRepository;
import com.example.activity.repository.ParticipantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final ParticipantRepository participantRepository;

    public ActivityService(ActivityRepository activityRepository, ParticipantRepository participantRepository) {
        this.activityRepository = activityRepository;
        this.participantRepository = participantRepository;
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

    public Participant addParticipant(Long activityId, String name) {
        Activity activity = findById(activityId);

        Optional<Participant> optionalParticipant = participantRepository.findAll()
                .stream()
                .filter(p -> p.getName().equals(name))
                .findFirst();

        Participant participant;

        if (optionalParticipant.isPresent()) {
            participant = optionalParticipant.get();
        } else {
            participant = new Participant();
            participant.setName(name);
        }

        participant.getActivities().add(activity);
        activity.getParticipants().add(participant);

        participantRepository.save(participant);
        activityRepository.save(activity);

        return participant;

    }

    public List<Participant> getParticipantsForActivity(Long activityId) {
        Activity activity = findById(activityId);
        return List.copyOf(activity.getParticipants());
    }
}
