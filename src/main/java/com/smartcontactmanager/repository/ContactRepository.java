package com.smartcontactmanager.repository;

import com.smartcontactmanager.entities.Contact;
import com.smartcontactmanager.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

    Page<Contact> findContactsByUser(User user, Pageable pageable);

    Page<Contact> findByNameContainingIgnoreCaseAndUser(String name, User user, Pageable pageable);

    Page<Contact> findContactsByRelationshipAndUser(String relationship, User user, Pageable pageable);
}
