package com.alumni.controller;

import com.alumni.model.Message;
import com.alumni.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "*")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public ResponseEntity<Message> sendMessage(@RequestBody Message message) {
        return ResponseEntity.ok(messageService.sendMessage(
            message.getSenderId(), 
            message.getReceiverId(), 
            message.getContent()
        ));
    }

    @GetMapping("/{user1}/{user2}")
    public ResponseEntity<List<Message>> getChatHistory(@PathVariable String user1, @PathVariable String user2) {
        return ResponseEntity.ok(messageService.getChatHistory(user1, user2));
    }
}
