package com.project.fitness.Controller;

import com.project.fitness.DTO.ActivityRequest;
import com.project.fitness.DTO.ActivityResponse;
import com.project.fitness.Service.ActivityService;
import com.project.fitness.model.Activity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://10.177.246.99:5173"
})
@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping("/bbt/track")
    public ResponseEntity<ActivityResponse> createActivity(@RequestBody ActivityRequest request){
        return ResponseEntity.ok(activityService.createActivity(request));
    }

    @GetMapping("/act/get")
    public ResponseEntity<List<ActivityResponse>> getUserActivities(@RequestHeader(value = "X-User-ID") String userId){
        return ResponseEntity.ok(activityService.getUserActivities(userId));
    }
}
