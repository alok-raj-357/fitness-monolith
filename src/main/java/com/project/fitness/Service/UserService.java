package com.project.fitness.Service;


import com.project.fitness.DTO.RegisterRequest;
import com.project.fitness.DTO.UserResponse;
import com.project.fitness.Repository.UserRepository;
import com.project.fitness.model.User;
import com.project.fitness.model.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;

@Service
 @RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponse register(RegisterRequest request) {
//        User user = new User(
//                null,
//                request.getEmail(),
//                request.getPassword(),
//                request.getFirstName(),
//                request.getLastName()
//        );
        UserRole role = (request.getRole() !=null)?request.getRole():UserRole.USER;

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .role(role)
                .build();                             //✔ RegisterRequest → User Entity me convert hota hai
        User savedUser = userRepository.save(user);   //✔ save() call hota hai
        return mapToResponse(savedUser);   // ✔ Entity → UserResponse DTO me convert hota hai
    }

    public UserResponse mapToResponse(User savedUser)
    {
        UserResponse response = new UserResponse();
        response.setUserId(savedUser.getUserId());
        response.setEmail(savedUser.getEmail());
        response.setFirstName(savedUser.getFirstName());
        response.setLastName(savedUser.getLastName());
        response.setCreatedAt(savedUser.getCreatedAt());
        return response;
    }

}


































//package com.project.fitness.Service;
//
//import com.project.fitness.Repository.UserRepository;
//import com.project.fitness.model.ddd;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserService {
//    @Autowired
//    private UserRepository userRepository;
//
//    public ddd message(String sms) {
//        ddd dd = new ddd(sms);
//        return userRepository.save(dd);
//    }
//}
