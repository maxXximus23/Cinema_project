package com.dut.CinemaProject.dao.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "jwt_blacklist", uniqueConstraints= @UniqueConstraint(columnNames={"token"}))
@Data
@EqualsAndHashCode
public class JwtBlacklist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String token;
}
