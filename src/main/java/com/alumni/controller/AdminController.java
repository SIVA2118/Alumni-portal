package com.alumni.controller;

import com.alumni.model.Alumni;
import com.alumni.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/pending")
    public ResponseEntity<List<Alumni>> getPendingApprovals() {
        return ResponseEntity.ok(adminService.getPendingApprovals());
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<Alumni> approveAlumni(@PathVariable String id) {
        return ResponseEntity.ok(adminService.approveAlumni(id));
    }

    @DeleteMapping("/alumni/{id}")
    public ResponseEntity<?> deleteAlumni(@PathVariable String id) {
        adminService.deleteAlumni(id);
        return ResponseEntity.ok().build();
    }
}
