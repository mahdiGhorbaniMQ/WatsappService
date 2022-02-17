package com.example.whatsapp.controllers.rest;

import com.example.whatsapp.models.Chatroom;
import com.example.whatsapp.payload.response.MessageResponse;
import com.example.whatsapp.repositories.ChatroomRepository;
import com.example.whatsapp.repositories.relations.UserUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/chatroom")
public class ChatroomController {
    @Autowired
    ChatroomRepository chatroomRepository;

    @Autowired
    UserUserRepository userUserRepository;

    @GetMapping("/add")
    private ResponseEntity<?> addChatroom(Principal principal, @RequestParam String contact){
        String username = principal.getName();
        if(chatroomRepository.getByUsernameAndContact(username,contact) == null && userUserRepository.getByUsernameAndContact(username,contact) != null){
            Chatroom chatroom = new Chatroom(username,contact);
            chatroomRepository.save(chatroom);
            return ResponseEntity.ok(new MessageResponse("chatroom successfully created!"));
        }
        else return ResponseEntity.badRequest().body(new MessageResponse("bad request!"));
    }
    @GetMapping("/remove")
    private ResponseEntity<?> removeChatroom(Principal principal, @RequestParam String contact){
        String username = principal.getName();
        Chatroom chatroom = chatroomRepository.getByUsernameAndContact(username,contact);
        if(chatroom != null){
            chatroomRepository.delete(chatroom);
            return ResponseEntity.ok(new MessageResponse("chatroom successfully removed!"));
        }
        else return ResponseEntity.badRequest().body(new MessageResponse("chatroom not found!"));
    }
    @GetMapping("/details")
    private ResponseEntity<?> getChatroomDetails(Principal principal, @RequestParam Long chatroomId){
        String username = principal.getName();
        Chatroom chatroom = chatroomRepository.getById(chatroomId);
        if(chatroom.getUsername1().equals(username) || chatroom.getUsername2().equals(username)){
            Chatroom responseChatroom = new Chatroom(chatroom.getUsername1(),chatroom.getUsername2());
            return ResponseEntity.ok(responseChatroom);
        }
        else return ResponseEntity.badRequest().body(new MessageResponse("permission denied!"));
    }
}
