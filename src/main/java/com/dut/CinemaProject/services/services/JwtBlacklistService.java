package com.dut.CinemaProject.services.services;

import com.dut.CinemaProject.dao.domain.JwtBlacklist;
import com.dut.CinemaProject.dao.repos.JwtBlacklistRepository;
import com.dut.CinemaProject.services.interfaces.IJwtBlacklistService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class JwtBlacklistService implements IJwtBlacklistService {

    private final JwtBlacklistRepository jwtBlacklistRepository;

    @Override
    public JwtBlacklist findByTokenEquals(String token) {
        return jwtBlacklistRepository.findByTokenEquals(token);
    }

}
