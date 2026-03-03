package com.alumni.controller;

import com.alumni.model.Event;
import com.alumni.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "*")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private com.alumni.repository.EventRegistrationRepository registrationRepository;

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/with-counts")
    public ResponseEntity<List<java.util.Map<String, Object>>> getEventsWithCounts() {
        List<Event> events = eventService.getAllEvents();
        List<java.util.Map<String, Object>> response = new java.util.ArrayList<>();
        for (Event ev : events) {
            java.util.Map<String, Object> map = new java.util.HashMap<>();
            map.put("event", ev);
            map.put("registrationCount", registrationRepository.findByEventId(ev.getId()).size());
            response.add(map);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        return ResponseEntity.ok(eventService.createEvent(event));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerForEvent(@RequestBody com.alumni.model.EventRegistration registration) {
        if (registration.getAlumniId() != null && 
            registrationRepository.existsByEventIdAndAlumniId(registration.getEventId(), registration.getAlumniId())) {
            return ResponseEntity.badRequest().body("You are already registered for this event.");
        }
        
        registration.setRegistrationTime(java.time.LocalDateTime.now());
        registrationRepository.save(registration);
        return ResponseEntity.ok("Successfully registered for the event!");
    }

    @GetMapping("/{id}/attendees")
    public ResponseEntity<List<com.alumni.model.EventRegistration>> getAttendees(@PathVariable String id) {
        return ResponseEntity.ok(registrationRepository.findByEventId(id));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable String id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok().build();
    }
}
