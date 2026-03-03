package com.alumni.service;

import com.alumni.model.Message;
import com.alumni.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message sendMessage(String senderId, String receiverId, String content) {
        Message message = new Message();
        message.setSenderId(senderId);
        message.setReceiverId(receiverId);
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        
        return messageRepository.save(message);
    }

    public List<Message> getChatHistory(String user1, String user2) {
        List<Message> sent = messageRepository.findBySenderIdAndReceiverIdOrderByTimestampAsc(user1, user2);
        List<Message> received = messageRepository.findBySenderIdAndReceiverIdOrderByTimestampAsc(user2, user1);
        
        sent.addAll(received);
        // Sort the combined list by timestamp
        sent.sort((m1, m2) -> m1.getTimestamp().compareTo(m2.getTimestamp()));
        
        return sent;
    }
}
