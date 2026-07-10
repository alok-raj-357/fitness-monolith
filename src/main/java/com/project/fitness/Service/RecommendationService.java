package com.project.fitness.Service;

import com.project.fitness.DTO.RecommendationRequest;
import com.project.fitness.Repository.ActivityRepository;
import com.project.fitness.Repository.RecommendationRepository;
import com.project.fitness.Repository.UserRepository;
import com.project.fitness.model.Activity;
import com.project.fitness.model.Recommendation;
import com.project.fitness.model.User;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;
    private final RecommendationRepository recommendationRepository;

    public @Nullable Recommendation generateRecommendation(RecommendationRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User Not Found"+ request.getUserId()));

        Activity activity = activityRepository.findById(request.getActivityId())
                .orElseThrow(()-> new RuntimeException("Activity Not Found"+request.getActivityId()));

        Recommendation recommendation = Recommendation.builder()
                .user(user)
                .activity(activity)
                .type(request.getType())
                .improvements(request.getImprovements())
                .recommendation(request.getRecommendation())
                .suggestions(request.getSuggestion())
                .safety(request.getSafety())
                .build();
        return recommendationRepository.save(recommendation);
    }

    public @Nullable List<Recommendation> getUserRecommendation(String userId) {
        return recommendationRepository.findByUserUserId(userId);
    }

    public @Nullable List<Recommendation> getActivityRecommendation(String activityId) {
        return recommendationRepository.findByActivityActivityId(activityId);
    }
}
