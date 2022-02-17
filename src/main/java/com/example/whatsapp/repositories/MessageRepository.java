package com.example.whatsapp.repositories;

import com.example.whatsapp.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {
    @Override
    Message getById(Long aLong);
}
