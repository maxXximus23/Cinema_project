package com.dut.CinemaProject.dao.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private Long id;

    @Getter @Setter private String title;
    @Getter @Setter private String description;
    @Column(name="poster_path")
    @Getter @Setter private String posterPath;
    @Column(name="trailer_path")
    @Getter @Setter private String trailerPath;
    @Getter @Setter private Integer duration;
}
