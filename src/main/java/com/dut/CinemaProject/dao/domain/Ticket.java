package com.dut.CinemaProject.dao.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private Long id;

    @Getter @Setter private Integer row;
    @Getter @Setter private Integer place;
    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    @Getter @Setter private Session session;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Getter @Setter private User customer;

}
