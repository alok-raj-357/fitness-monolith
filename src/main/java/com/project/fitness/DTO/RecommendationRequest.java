package com.project.fitness.DTO;

import com.project.fitness.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationRequest {
    private String userId;
    private String activityId;
    private String type;
    private List<String> recommendation;
    private List<String> improvements;
    private List<String> suggestion;
    private List<String> safety;
    private UserRole role;
}
