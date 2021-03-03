package com.dut.CinemaProject.services.services;

import com.dut.CinemaProject.dao.repos.SessionRepository;
import com.dut.CinemaProject.dto.Session.SessionDto;
import com.dut.CinemaProject.services.interfaces.ISessionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SessionService implements ISessionService {
    private SessionRepository sessionRepository;

    @Override
    public List<SessionDto> getActualSessions() {
       return sessionRepository.findAll().stream()
               .filter(e -> e.getDate().isAfter(LocalDateTime.now()))
               .map(SessionDto::new)
               .collect(Collectors.toList());
    }
}
