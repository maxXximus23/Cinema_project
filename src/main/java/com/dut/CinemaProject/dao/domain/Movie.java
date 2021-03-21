package com.dut.CinemaProject.dao.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "movies")
@Getter @Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;
    @Column(name="poster_path")
    private String posterPath;
    @Column(name="trailer_path")
    private String trailerPath;
    private Integer duration;
    private String actors;
    private String genres;
    private String country;
}
