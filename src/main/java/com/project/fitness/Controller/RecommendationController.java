package com.project.fitness.Controller;

import com.project.fitness.DTO.RecommendationRequest;
import com.project.fitness.Service.RecommendationService;
import com.project.fitness.model.Recommendation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://10.177.246.99:5173"
})
@RestController
@RequestMapping("/api/recommendation")
@RequiredArgsConstructor
public class RecommendationController {
    private final RecommendationService recommendationService;

    @PostMapping("generate")
    public ResponseEntity<Recommendation> generateRecommendation(@RequestBody RecommendationRequest request){
        return ResponseEntity.ok(recommendationService.generateRecommendation(request));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Recommendation>> getUserRecommendation(@PathVariable String userId ){
        return ResponseEntity.ok(recommendationService.getUserRecommendation(userId));
    }
    @GetMapping("/activity/{activityId}")
    public ResponseEntity<List<Recommendation>> getActivityRecommendation(@PathVariable String activityId){
        return ResponseEntity.ok(recommendationService.getActivityRecommendation(activityId));
    }
}
