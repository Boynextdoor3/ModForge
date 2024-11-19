package com.coursework.modforge.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

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

    @Column(name = "title")
    private String title;

    @Column(name = "description")
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

    @CreationTimestamp
    @Column(name = "added_date")
    private LocalDate addedDate;
}

