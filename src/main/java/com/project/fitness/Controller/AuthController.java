package com.project.fitness.Controller;

import com.project.fitness.DTO.LoginRequest;
import com.project.fitness.DTO.LoginResponse;
import com.project.fitness.DTO.RegisterRequest;
import com.project.fitness.DTO.UserResponse;
import com.project.fitness.Repository.UserRepository;
import com.project.fitness.Service.UserService;
import com.project.fitness.model.User;
import com.project.fitness.security.JwtUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "http://localhost:5173")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://10.177.246.99:5173"
})
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<UserResponse>register(@Valid @RequestBody RegisterRequest registerRequest){              // RegisterRequest object me convert hota hai
        return ResponseEntity.ok(userService.register(registerRequest));                                          // Controller service ko bolta hai: 👉 "Ye data lo aur process karo"
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        Authentication authentication;
        try {
            User user = userRepository.findByEmail(loginRequest.getEmail());
            if (user == null) return ResponseEntity.status(401).build();

            if(!passwordEncoder.matches(loginRequest.getPassword(),user.getPassword())){
                return ResponseEntity.status(401).build();
            }

            String token = jwtUtils.generateToken(user.getEmail(), user.getRole().name());
            return ResponseEntity.ok(new LoginResponse(token,user.getUserId()));

        }catch (AuthenticationException e){
            e.printStackTrace();
            return ResponseEntity.status(401).build();
        }
    }
}
