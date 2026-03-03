package com.alumni.repository;

import com.alumni.model.Connection;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface ConnectionRepository extends MongoRepository<Connection, String> {
    List<Connection> findByReceiverIdAndStatus(String receiverId, String status);
    List<Connection> findByRequesterIdAndStatus(String requesterId, String status);
    Optional<Connection> findByRequesterIdAndReceiverId(String requesterId, String receiverId);
}
