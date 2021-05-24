package com.onemount.cinema.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "room")
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public Room(String name) {
        this.name = name;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Cinema cinema;

    @OneToMany(mappedBy = "room")
    private List<Event> events = new ArrayList<>();
}
