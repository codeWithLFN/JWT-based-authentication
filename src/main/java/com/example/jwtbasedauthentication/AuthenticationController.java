package com.example.jwtbasedauthentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.jwtbasedauthentication.JwtTokenUtil;

@RestController
@RequestMapping("/api")
public class AuthenticationController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();

        if (UserDetails.authenticate(username, password)) {
            String token = jwtTokenUtil.generateToken(username);
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }
    }
}