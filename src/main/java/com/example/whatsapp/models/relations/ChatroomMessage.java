package com.example.whatsapp.models.relations;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
public class ChatroomMessage {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id @Getter @Setter @Column
    private Long id;

    @Getter @Setter @Column
    private Long chatroomId;

    @Getter @Setter @Column
    private Long messageId;

    public ChatroomMessage(){}
    public ChatroomMessage(Long chatroomId ,Long messageId){
        this.chatroomId = chatroomId;
        this.messageId = messageId;
    }

}
