package com.project.fitness.Repository;

import com.project.fitness.model.Recommendation;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, String> {

    @Nullable List<Recommendation> findByUserUserId(String userId);

    @Nullable List<Recommendation> findByActivityActivityId(String activityId);
}
