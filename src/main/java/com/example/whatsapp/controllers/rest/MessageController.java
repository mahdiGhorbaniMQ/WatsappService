package com.example.whatsapp.controllers.rest;

import com.example.whatsapp.models.Chatroom;
import com.example.whatsapp.models.Message;
import com.example.whatsapp.models.relations.ChatroomMessage;
import com.example.whatsapp.repositories.MessageRepository;
import com.example.whatsapp.repositories.relations.ChatroomMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/message")
public class MessageController {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    ChatroomMessageRepository chatroomMessageRepository;

    @GetMapping("all")
    private ResponseEntity<Set<Message>> getAllByChatroomId(Principal principal, @RequestParam Long chatroomId){
        String username = principal.getName();
        Collection<ChatroomMessage> chatroomMessageRelations = chatroomMessageRepository.findAllByChatroomId(chatroomId);
        Set<Message> messages = new HashSet<>();
        chatroomMessageRelations.forEach(item->{
            Message message = messageRepository.getById(item.getMessageId());
            messages.add(message);
        });
        return ResponseEntity.ok(messages);
    }
}
