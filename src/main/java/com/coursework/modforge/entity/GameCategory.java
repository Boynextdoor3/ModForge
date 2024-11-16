package com.coursework.modforge.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "game_categorys")
public class GameCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Game>games;
}
