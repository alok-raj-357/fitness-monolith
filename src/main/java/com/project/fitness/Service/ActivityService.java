package com.project.fitness.Service;

import com.project.fitness.DTO.ActivityRequest;
import com.project.fitness.DTO.ActivityResponse;
import com.project.fitness.Repository.ActivityRepository;
import com.project.fitness.Repository.UserRepository;
import com.project.fitness.model.Activity;
import com.project.fitness.model.User;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;

    public @Nullable ActivityResponse createActivity(ActivityRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(()-> new RuntimeException("Invalid user: "+ request.getUserId()));

        Activity activity = Activity.builder()
                .user(user)
                .type(request.getType())
                .duration(request.getDuration())
                .caloriesBurned(request.getCaloriesBurned())
                .additionalMatrices(request.getAdditionalMatrices())
                .build();
        Activity savedactivity = activityRepository.save(activity);
        return maptoResponse(savedactivity);
    }

    private @Nullable ActivityResponse maptoResponse(Activity activity) {
        ActivityResponse response = new ActivityResponse();
        response.setActivityId(activity.getActivityId());
        response.setUserId(activity.getUser().getUserId());
        response.setType(activity.getType());
        response.setDuration(activity.getDuration());
        response.setCaloriesBurned(activity.getCaloriesBurned());
        response.setStartedAt(activity.getStartTime());
        response.setAdditionalMatrices(activity.getAdditionalMatrices());
        return response;
    }

    public @Nullable List<ActivityResponse> getUserActivities(String userId) {
        List<Activity> activityList = activityRepository.findByUserUserId(userId);
//        1. Activity --> convert in ActivityResponse
//                2. Collect in List and return

        return activityList.stream()
                .map(this::maptoResponse)
                .collect(Collectors.toList());
    }
}
