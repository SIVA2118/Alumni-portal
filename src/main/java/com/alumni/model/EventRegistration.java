package com.alumni.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "event_registrations")
public class EventRegistration {
    @Id
    private String id;
    private String eventId;
    private String alumniId; // Can be username or ID
    private String fullName;
    private String email;
    private LocalDateTime registrationTime;
}
