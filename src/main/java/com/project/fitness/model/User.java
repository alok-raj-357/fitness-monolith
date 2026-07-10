package com.project.fitness.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data               // FOR GETTER SETTER
@NoArgsConstructor  // FOR DEFAULT CONSTRUCTOR
@AllArgsConstructor   // FOR PARAMETERIZED CONSTRUCTOR
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)   //  primary key ko UUID format me auto-generate karne ke liye use hota hai.
    private String userId;

    @Column(unique = true)
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;  //BYDEFAULT ROLE WILL BE USER if the user created

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
    
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL , orphanRemoval = true)
    @JsonIgnore
    private List<Activity> activities = new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL , orphanRemoval = true)
    @JsonIgnore
    private List<Recommendation> recommendations = new ArrayList<>();

}
