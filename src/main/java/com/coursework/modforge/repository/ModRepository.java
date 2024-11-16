package com.coursework.modforge.repository;

import com.coursework.modforge.entity.Mod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModRepository extends JpaRepository<Mod, Long> {
}
