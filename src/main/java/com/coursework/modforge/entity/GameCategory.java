package com.coursework.modforge.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "game_categorys")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GameCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Game>games;
}
