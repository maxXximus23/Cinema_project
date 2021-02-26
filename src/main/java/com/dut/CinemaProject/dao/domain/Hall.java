package com.dut.CinemaProject.dao.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "halls")
public class Hall {
    @Id
    private Long id;
}
