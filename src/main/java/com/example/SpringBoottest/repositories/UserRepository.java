package com.example.SpringBoottest.repositories;

import com.example.SpringBoottest.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}
