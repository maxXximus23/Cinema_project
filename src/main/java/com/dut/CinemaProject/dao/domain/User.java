package com.dut.CinemaProject.dao.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usrs")
public class User {
    @Id
    private Long id;
}
