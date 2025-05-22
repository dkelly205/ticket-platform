package com.dkelly205.tickets.repository;

import com.dkelly205.tickets.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
