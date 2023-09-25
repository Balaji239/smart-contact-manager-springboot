package com.smartcontactmanager.repository;

import com.smartcontactmanager.entities.Contact;
import com.smartcontactmanager.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

    Page<Contact> findContactsByUser(User user, Pageable pageable);

    Page<Contact> findByNameContainingIgnoreCaseAndUser(String name, User user, Pageable pageable);

    Page<Contact> findContactsByRelationshipAndUser(String relationship, User user, Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE Contact c SET c.isFavorite =:isFavourite WHERE c.id = :conId")
    int updateFavourite(@Param("isFavourite") boolean isFavourite, @Param("conId") int id);
}
