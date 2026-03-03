package com.alumni.service;

import com.alumni.model.Connection;
import com.alumni.repository.ConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConnectionService {

    @Autowired
    private ConnectionRepository connectionRepository;

    public Connection sendRequest(String requesterId, String receiverId) {
        // Check if a connection already exists
        Optional<Connection> existing1 = connectionRepository.findByRequesterIdAndReceiverId(requesterId, receiverId);
        Optional<Connection> existing2 = connectionRepository.findByRequesterIdAndReceiverId(receiverId, requesterId);
        
        if (existing1.isPresent() || existing2.isPresent()) {
            throw new RuntimeException("Connection request already exists or is accepted.");
        }

        Connection connection = new Connection();
        connection.setRequesterId(requesterId);
        connection.setReceiverId(receiverId);
        connection.setStatus("PENDING");
        return connectionRepository.save(connection);
    }

    public List<Connection> getPendingRequests(String receiverId) {
        return connectionRepository.findByReceiverIdAndStatus(receiverId, "PENDING");
    }
    
    public List<Connection> getAcceptedConnections(String userId) {
        List<Connection> asReceiver = connectionRepository.findByReceiverIdAndStatus(userId, "ACCEPTED");
        List<Connection> asRequester = connectionRepository.findByRequesterIdAndStatus(userId, "ACCEPTED");
        asReceiver.addAll(asRequester);
        return asReceiver;
    }

    public Connection acceptRequest(String connectionId) {
        Connection connection = connectionRepository.findById(connectionId)
            .orElseThrow(() -> new RuntimeException("Connection not found"));
        
        connection.setStatus("ACCEPTED");
        return connectionRepository.save(connection);
    }

    public List<Connection> getAllUserConnections(String userId) {
        List<Connection> asReceiver = connectionRepository.findByReceiverIdAndStatus(userId, "ACCEPTED");
        List<Connection> asRequester = connectionRepository.findByRequesterIdAndStatus(userId, "ACCEPTED");
        asReceiver.addAll(asRequester);

        List<Connection> pendingReceived = connectionRepository.findByReceiverIdAndStatus(userId, "PENDING");
        List<Connection> pendingSent = connectionRepository.findByRequesterIdAndStatus(userId, "PENDING");
        
        asReceiver.addAll(pendingReceived);
        asReceiver.addAll(pendingSent);
        return asReceiver;
    }
}
