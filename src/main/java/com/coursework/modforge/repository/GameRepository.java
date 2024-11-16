package com.coursework.modforge.repository;

import com.coursework.modforge.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}