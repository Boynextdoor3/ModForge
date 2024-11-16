package com.coursework.modforge.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mods")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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

