package com.dut.CinemaProject.dao.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sessions")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private Long id;

    @Getter @Setter private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    @Getter @Setter private Movie movie;
    @ManyToOne
    @JoinColumn(name = "hall_id", nullable = false)
    @Getter @Setter private Hall hall;
}
