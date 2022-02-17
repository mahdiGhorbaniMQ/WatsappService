package com.example.whatsapp.repositories;

import com.example.whatsapp.models.Chatroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface ChatroomRepository extends JpaRepository<Chatroom,Long> {
    @Query("SELECT u FROM Chatroom u WHERE u.username1 = ?1 OR u.username2 = ?1")
    Collection<Chatroom> findAllByUsername(String username);

    @Query("SELECT u FROM Chatroom u WHERE (u.username1 = ?1 AND u.username2 = ?2) OR (u.username1 = ?2 AND u.username2 = ?1)")
    Chatroom getByUsernameAndContact(String username, String contact);

    @Override
    Chatroom getById(Long aLong);
}
