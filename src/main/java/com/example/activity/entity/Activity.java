package com.example.activity.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String title;
    private String description;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate startDate;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;
    private String location;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate createdAt = LocalDate.now();

    @ManyToMany(mappedBy = "activities")
    private Set<Participant> participants = new HashSet<>();



    public void addParticipant(Participant participant) {
        participants.add(participant);
        participant.getActivities().add(this);
    }


    public void removeParticipant(Participant participant) {
        participants.remove(participant);
        participant.getActivities().remove(this);
    }
}
