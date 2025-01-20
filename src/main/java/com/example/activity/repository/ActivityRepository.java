package com.example.activity.repository;

import com.example.activity.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity,Long> {
}
