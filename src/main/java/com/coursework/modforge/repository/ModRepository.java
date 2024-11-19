package com.coursework.modforge.repository;

import com.coursework.modforge.entity.Mod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ModRepository extends JpaRepository<Mod, Long>, JpaSpecificationExecutor<Mod> {
}
