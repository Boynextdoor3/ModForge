package com.coursework.modforge.repository;

import com.coursework.modforge.entity.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
    Page<Game> findAll(Specification<Game> specification, Pageable pageable);

    boolean existsByTitle(String title);
}