package com.example.whatsapp.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Chatroom {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id @Getter @Setter @Column
    private Long id;

    @Getter @Setter @Column
    private String username1;

    @Getter @Setter @Column
    private String username2;

    public Chatroom(){}
    public Chatroom(String username1, String username2){
        this.username1 = username1;
        this.username2 = username2;
    }
}
