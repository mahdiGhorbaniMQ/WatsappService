package com.example.whatsapp.repositories.relations;

import com.example.whatsapp.models.relations.ChatroomMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface ChatroomMessageRepository extends JpaRepository<ChatroomMessage,Long> {
    @Query("SELECT u FROM ChatroomMessage u WHERE u.chatroomId = ?1")
    Collection<ChatroomMessage> findAllByChatroomId(Long chatroomId);
}
