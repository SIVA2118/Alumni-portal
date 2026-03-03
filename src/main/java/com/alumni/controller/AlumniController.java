package com.alumni.controller;

import com.alumni.model.Alumni;
import com.alumni.service.AlumniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alumni")
@CrossOrigin(origins = "*")
public class AlumniController {

    @Autowired
    private AlumniService alumniService;

    @GetMapping
    public ResponseEntity<List<Alumni>> getAllApprovedAlumni() {
        return ResponseEntity.ok(alumniService.getAllApprovedAlumni());
    }

    @GetMapping("/search/department")
    public ResponseEntity<List<Alumni>> searchByDepartment(@RequestParam String department) {
        return ResponseEntity.ok(alumniService.searchByDepartment(department));
    }

    @GetMapping("/search/batch")
    public ResponseEntity<List<Alumni>> searchByBatchYear(@RequestParam Integer year) {
        return ResponseEntity.ok(alumniService.searchByBatchYear(year));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alumni> updateProfile(@PathVariable String id, @RequestBody Alumni alumni) {
        return ResponseEntity.ok(alumniService.updateProfile(id, alumni));
    }
}
