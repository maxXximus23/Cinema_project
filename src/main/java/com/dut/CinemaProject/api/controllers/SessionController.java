package com.dut.CinemaProject.api.controllers;

import com.dut.CinemaProject.dto.Session.SessionDto;
import com.dut.CinemaProject.dto.Session.SessionTicketsList;
import com.dut.CinemaProject.services.interfaces.ISessionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sessions")
@AllArgsConstructor
public class SessionController {
    private ISessionService sessionService;

    @GetMapping("/actual")
    @ResponseStatus(HttpStatus.OK)
    public List<SessionDto> getActual(){
        return sessionService.getActualSessions();
    }
  
    @GetMapping("/{id}/tickets")
    @ResponseStatus(HttpStatus.OK)
    public SessionTicketsList getTicketsList(@PathVariable Long id){
        return sessionService.getSessionTicketsData(id);
    }

}
