package com.onemount.cinema.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "actor")
@Table(name = "actor")
@Data
@NoArgsConstructor
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "full_name")
    private String fullName;

    private int age;

    private String country;

    @ManyToMany(
        cascade = CascadeType.ALL,
        mappedBy = "actors", 
        fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Film> films = new ArrayList<>();

    public Actor(String name, int age, String country){
        this.name = name;
        this.age = age;
        this.country = country;
    }

    public Actor(String name, String country){
        this.name = name;
        this.country = country;
    }
}
