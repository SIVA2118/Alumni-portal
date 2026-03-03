package com.alumni.controller;

import com.alumni.model.Connection;
import com.alumni.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/connections")
@CrossOrigin(origins = "*")
public class ConnectionController {

    @Autowired
    private ConnectionService connectionService;

    @PostMapping("/request")
    public ResponseEntity<?> sendRequest(@RequestParam String requesterId, @RequestParam String receiverId) {
        try {
            return ResponseEntity.ok(connectionService.sendRequest(requesterId, receiverId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/pending/{userId}")
    public ResponseEntity<List<Connection>> getPendingRequests(@PathVariable String userId) {
        return ResponseEntity.ok(connectionService.getPendingRequests(userId));
    }
    
    @GetMapping("/accepted/{userId}")
    public ResponseEntity<List<Connection>> getAcceptedConnections(@PathVariable String userId) {
        return ResponseEntity.ok(connectionService.getAcceptedConnections(userId));
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<Connection>> getAllConnections(@PathVariable String userId) {
        return ResponseEntity.ok(connectionService.getAllUserConnections(userId));
    }

    @PutMapping("/accept/{connectionId}")
    public ResponseEntity<?> acceptRequest(@PathVariable String connectionId) {
        try {
            return ResponseEntity.ok(connectionService.acceptRequest(connectionId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
