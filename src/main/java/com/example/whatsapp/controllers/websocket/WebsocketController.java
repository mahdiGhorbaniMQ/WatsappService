package com.example.whatsapp.controllers.websocket;

import com.example.whatsapp.models.Message;
import com.example.whatsapp.models.relations.ChatroomMessage;
import com.example.whatsapp.repositories.MessageRepository;
import com.example.whatsapp.repositories.relations.ChatroomMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class WebsocketController {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    ChatroomMessageRepository chatroomMessageRepository;

    @MessageMapping("/{id}/messages")
    @SendTo("/chatroom/{id}/messages")
    public Message sendMessage(Message message) throws Exception {
        Message newMessage = new Message(
                message.getWriter(),
                message.getContent(),
                message.getChatroomId()
        );
        messageRepository.save(newMessage);
        ChatroomMessage chatroomMessageRelation = new ChatroomMessage(newMessage.getChatroomId(),newMessage.getId());
        chatroomMessageRepository.save(chatroomMessageRelation);
        return newMessage;
    }
}
