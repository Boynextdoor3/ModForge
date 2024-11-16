package com.coursework.modforge.repository;

import com.coursework.modforge.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
