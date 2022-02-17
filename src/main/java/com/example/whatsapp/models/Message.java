package com.example.whatsapp.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Message {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id @Getter @Setter @Column
    private Long id;

    @Getter @Setter @Column
    private String writer;

    @Getter @Setter @Column
    private String content;

    @Getter @Setter @Column
    private Date date;

    @Getter @Setter @Column
    private Long chatroomId;

    public Message(){}
    public Message(String writer, String content,Long chatroomId){
        this.writer = writer;
        this.content = content;
        this.chatroomId = chatroomId;
        this.date = new Date();
    }
}
