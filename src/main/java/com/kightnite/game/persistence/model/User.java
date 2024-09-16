package com.kightnite.game.persistence.model;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USERS")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Generated
    private String name;

    private int score;

    public User(){}

    public User(String name, int score){
        this.name = name;
        this.score = score;
    }
}
