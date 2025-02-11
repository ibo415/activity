package com.example.activity.service;

import com.example.activity.entity.Activity;
import com.example.activity.entity.Participant;
import com.example.activity.exception.DuplicateParticipantException;
import com.example.activity.exception.ResourceNotFoundException;
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
                () -> new ResourceNotFoundException("activity not found with id: " + id));

    }

    public Participant addParticipant(Long activityId, String name) {

        if (activityId == null || name == null || name.isBlank()) {
            throw new IllegalArgumentException("activity ID and participant name must not be null or blank");
        }


        Activity activity = findById(activityId); // aktivitaet suchen

        // Prüfen, ob der Benutzer bereits als Teilnehmer in der Aktivität ist
        if (participantRepository.findByNameAndActivity(name, activityId).isPresent()) {
            throw new DuplicateParticipantException("User " + name + " is already registered in activity " + activity.getTitle());
        }

        // Teilnehmer aus der Datenbank abrufen oder erstellen
        Optional<Participant> optionalParticipant = participantRepository.findByName(name);
        Participant participant = optionalParticipant.orElseGet(() -> {
            Participant newParticipant = new Participant();
            newParticipant.setName(name);
            return newParticipant;
        });


        activity.addParticipant(participant);

        participantRepository.save(participant); // speichert sowohl die Teilnehmer als auch die Beziehungen
        return participant;

    }

    public List<Participant> getParticipantsForActivity(Long activityId) {
        Activity activity = findById(activityId);
        return List.copyOf(activity.getParticipants());
    }
}
