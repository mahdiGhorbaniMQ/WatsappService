package com.example.whatsapp.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

public class UserDetailsResponse {
    @Getter @Setter private Long id;
    @Getter @Setter private String firstName;
    @Getter @Setter private String lastName;
    @Getter @Setter private String username;
    @Getter @Setter private String email;
    @Getter @Setter private Set<Long> chatrooms = new HashSet<>();
    @Getter @Setter private Set<String> contacts = new HashSet<>();

    public UserDetailsResponse(){}
    public UserDetailsResponse(Long id, String firstName, String lastName, String email, String username, Set<Long> chatrooms, Set<String> contacts){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.chatrooms = chatrooms;
        this.contacts = contacts;
    }
}
