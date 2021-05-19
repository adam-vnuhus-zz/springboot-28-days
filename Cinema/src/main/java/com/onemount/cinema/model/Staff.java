package com.onemount.cinema.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity(name = "staff")
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fullName;

    private String staffCode;

    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cinema_id")
    private Cinema cinema;
}
