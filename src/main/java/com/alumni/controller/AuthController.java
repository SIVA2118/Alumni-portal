package com.alumni.controller;

import com.alumni.model.Admin;
import com.alumni.model.Alumni;
import com.alumni.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerAlumni(@RequestBody Alumni alumni) {
        try {
            Alumni registered = authService.registerAlumni(alumni);
            return ResponseEntity.ok(Map.of("message", "Registration successful. Pending admin approval."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginAlumni(@RequestBody Map<String, String> credentials) {
        try {
            Alumni alumni = authService.loginAlumni(credentials.get("email"), credentials.get("password"));
            return ResponseEntity.ok(alumni);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
    @PostMapping("/admin/login")
    public ResponseEntity<?> loginAdmin(@RequestBody Map<String, String> credentials) {
        try {
            Admin admin = authService.loginAdmin(credentials.get("username"), credentials.get("password"));
            return ResponseEntity.ok(admin);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
