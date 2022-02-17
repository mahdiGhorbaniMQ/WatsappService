package com.example.whatsapp.controllers.rest;

import com.example.whatsapp.models.Chatroom;
import com.example.whatsapp.models.relations.UserUser;
import com.example.whatsapp.payload.response.MessageResponse;
import com.example.whatsapp.repositories.UserRepository;
import com.example.whatsapp.repositories.relations.UserUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    UserUserRepository userUserRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/add")
    private ResponseEntity<?> addContact(Principal principal, @RequestParam String contact){
        String username = principal.getName();
        if(username != contact && userRepository.existsByUsername(contact) && userUserRepository.getByUsernameAndContact(username,contact) == null){
            UserUser userUser = new UserUser(username,contact);
            userUserRepository.save(userUser);
            return ResponseEntity.ok(new MessageResponse("contact successfully added!"));
        }
        else return ResponseEntity.badRequest().body(new MessageResponse("bad request!"));
    }
    @GetMapping("/remove")
    private ResponseEntity<?> removeContact(Principal principal, @RequestParam String contact){
        String username = principal.getName();
        UserUser userUser = userUserRepository.getByUsernameAndContact(username,contact);
        if(userUser != null){
            userUserRepository.delete(userUser);
            return ResponseEntity.ok(new MessageResponse("contact successfully removed!"));
        }
        else return ResponseEntity.badRequest().body(new MessageResponse("contact not found!"));
    }

}
