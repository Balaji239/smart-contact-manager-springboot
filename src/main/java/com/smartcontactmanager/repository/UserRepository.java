package com.smartcontactmanager.repository;

import com.smartcontactmanager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findUserByEmail(String email);

    User findUserByFirstName(String name);
}
