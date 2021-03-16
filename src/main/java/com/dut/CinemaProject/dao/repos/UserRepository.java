package com.dut.CinemaProject.dao.repos;

import com.dut.CinemaProject.dao.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
}
