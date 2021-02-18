package com.khandora.it.repository;

import com.khandora.it.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String name);
}
