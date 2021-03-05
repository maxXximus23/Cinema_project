package com.dut.CinemaProject.api.controllers;

import com.dut.CinemaProject.dto.Session.SessionDto;
import com.dut.CinemaProject.services.interfaces.ISessionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("sessions")
@AllArgsConstructor
public class SessionController {
    private ISessionService sessionService;

    @GetMapping("/actual")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<SessionDto>> getActual(){
        return ResponseEntity.ok(sessionService.getActualSessions());
    }
}
