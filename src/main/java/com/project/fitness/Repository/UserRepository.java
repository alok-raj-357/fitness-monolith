package com.project.fitness.Repository;

import com.project.fitness.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);                                      // ✔ JpaRepository automatically SQL banata hai
                                                                        // ✔ Data database me save hota hai
                                                                       // ✔ ID auto-generate hota hai (UUID)
}

































//package com.project.fitness.Repository;
//
//import com.project.fitness.model.ddd;
//import jakarta.persistence.Id;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface UserRepository extends JpaRepository<ddd,String> {
//}
