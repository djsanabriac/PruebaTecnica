package url.clasification.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import url.clasification.backend.dto.GeneralResponse;
import url.clasification.backend.dto.User;
import url.clasification.backend.repository.UserRepository;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController{


    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody Map<String, String> body){

        if( body.get("username") == null || body.get("password") == null ){
            return ResponseEntity.badRequest().body(new GeneralResponse(false, "null_parameters"));
        }
        User user = new User(body.get("username"), new BCryptPasswordEncoder().encode(body.get("password")));
        User newUser = userRepository.save(user);
        return ResponseEntity.ok(new GeneralResponse(true, "registration_successfull", newUser.getId()));
    }

}