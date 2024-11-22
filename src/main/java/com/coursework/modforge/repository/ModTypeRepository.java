package com.coursework.modforge.repository;

import com.coursework.modforge.entity.ModType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModTypeRepository extends JpaRepository<ModType, Long> {
    boolean existsByName(String name);
}
