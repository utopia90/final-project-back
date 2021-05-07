package com.example.repository;

import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<User,Long>
{

    User findUserByEmail(String email);
    Boolean existsUserByEmail(String email);
}
