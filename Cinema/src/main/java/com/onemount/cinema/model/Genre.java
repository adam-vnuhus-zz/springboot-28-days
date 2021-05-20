package com.onemount.cinema.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity(name = "genre")
@Table(name = "genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany(
        cascade = CascadeType.ALL, 
        mappedBy = "genres", 
        fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Film> films = new ArrayList<>();

    public Genre(String name) {
        this.name = name;
    }

}
