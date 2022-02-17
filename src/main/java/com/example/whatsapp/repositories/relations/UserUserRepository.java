package com.example.whatsapp.repositories.relations;

import com.example.whatsapp.models.relations.UserUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface UserUserRepository extends JpaRepository<UserUser,Long> {
    @Query("SELECT u FROM UserUser u WHERE u.username = ?1")
    Collection<UserUser> findAllByUsername(String username);

    @Query("SELECT u FROM UserUser u WHERE u.username = ?1 AND u.contact = ?2")
    UserUser getByUsernameAndContact(String username, String contact);
}
