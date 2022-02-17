package com.example.whatsapp.controllers.rest;

import com.example.whatsapp.models.Chatroom;
import com.example.whatsapp.models.User;
import com.example.whatsapp.models.relations.UserUser;
import com.example.whatsapp.payload.response.UserDetailsResponse;
import com.example.whatsapp.payload.response.UserProfileResponse;
import com.example.whatsapp.repositories.ChatroomRepository;
import com.example.whatsapp.repositories.UserRepository;
import com.example.whatsapp.repositories.relations.UserUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ChatroomRepository chatroomRepository;

    @Autowired
    UserUserRepository userUserRepository;

    @GetMapping("/details")
    public ResponseEntity<?> getUserDetails(Principal principal) {

        String username = principal.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        Collection<Chatroom> chatrooms = chatroomRepository.findAllByUsername(username);
        Set<Long> userChatrooms = new HashSet<>();
        chatrooms.forEach(item->{
            userChatrooms.add(item.getId());
        });
        Collection<UserUser> userUserRelations = userUserRepository.findAllByUsername(username);
        Set<String> userContacts = new HashSet<>();
        userUserRelations.forEach(item->{
            userContacts.add(item.getContact());
        });
        return ResponseEntity.ok(new UserDetailsResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getUsername(),
                userChatrooms,
                userContacts));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProfile(Principal principal,@RequestBody User newUser) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        userRepository.save(user);
        return ResponseEntity.ok("account successfully updated!");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        Collection<UserUser> userUserRelations = userUserRepository.findAllByUsername(username);
        userUserRelations.forEach(item->{
            userUserRepository.delete(item);
        });
        userRepository.delete(user);
        return ResponseEntity.ok("account successfully deleted!");
    }

    @GetMapping("profile")
    public ResponseEntity<?> getUserProfile(@RequestParam String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return ResponseEntity.ok(new UserProfileResponse(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getUsername()));
    }

}
