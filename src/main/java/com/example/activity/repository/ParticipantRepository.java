package com.example.activity.repository;

import com.example.activity.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ParticipantRepository extends JpaRepository<Participant,Long> {
    Optional<Participant> findByName(String name);

    @Query("SELECT p FROM Participant p JOIN p.activities a WHERE p.name = :name AND a.id = :activityId")
    Optional<Participant> findByNameAndActivity(@Param("name") String name, @Param("activityId") Long activityId);
}
