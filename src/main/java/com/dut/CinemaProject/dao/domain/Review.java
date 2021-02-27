package com.dut.CinemaProject.dao.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private Long id;

    @Getter @Setter private String text;
    @Getter @Setter private Integer mark;
    @Column(name="creation_date")
    @Getter @Setter private LocalDateTime creationDate;
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    @Getter @Setter private Movie movie;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Getter @Setter private User author;
}
