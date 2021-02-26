package com.dut.CinemaProject.dao.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sessions")
public class Session {
    @Id
    private Long id;
}
