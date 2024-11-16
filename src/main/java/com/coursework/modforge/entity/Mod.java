package com.coursework.modforge.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "mods")
public class Mod {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private ModType type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User modCreator;
}

