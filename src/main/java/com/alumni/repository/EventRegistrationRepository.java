package com.alumni.repository;

import com.alumni.model.EventRegistration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRegistrationRepository extends MongoRepository<EventRegistration, String> {
    List<EventRegistration> findByEventId(String eventId);
    boolean existsByEventIdAndAlumniId(String eventId, String alumniId);
}
