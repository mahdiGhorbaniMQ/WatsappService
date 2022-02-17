package com.example.whatsapp.models.relations;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class UserUser {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id @Getter @Setter @Column
    private Long id;

    @Getter @Setter @Column
    private String username;

    @Getter @Setter @Column
    private String contact;

    public UserUser(){}
    public UserUser(String username, String contact){
       this.username = username;
       this.contact = contact;
    }

}