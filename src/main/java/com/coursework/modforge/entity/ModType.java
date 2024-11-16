package com.coursework.modforge.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "mod_types")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ModType {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany(mappedBy = "type")
    private List<Mod> mods;
}
