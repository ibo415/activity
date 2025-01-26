package com.example.activity.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include

    private Long id;
    private String name;
    @ManyToMany
    @JoinTable(
            name = "activity_participants",
            joinColumns = @JoinColumn(name = "participant_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id")
    )
    @JsonIgnore
    private Set<Activity> activities = new HashSet<>();

    public void addActivity(Activity activity) {
        activities.add(activity);
        activity.getParticipants().add(this);
    }

    public void removeActivity(Activity activity) {
        activities.remove(activity);
        activity.getParticipants().remove(this);
    }

}
