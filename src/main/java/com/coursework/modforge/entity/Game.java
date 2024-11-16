package com.coursework.modforge.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private GameCategory category;

    @OneToMany(mappedBy = "game")
    private List<Mod> mods;
}

