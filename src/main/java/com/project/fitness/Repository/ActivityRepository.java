package com.project.fitness.Repository;

import com.project.fitness.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ActivityRepository extends JpaRepository<Activity , String> {  // Activity :- Model  and String :- PK(ID)

    List<Activity> findByUserUserId(String userId);  // custom method
}
