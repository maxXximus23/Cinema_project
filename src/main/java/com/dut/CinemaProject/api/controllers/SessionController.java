package com.dut.CinemaProject.api.controllers;

import com.dut.CinemaProject.dto.Session.SessionTicketsList;
import com.dut.CinemaProject.services.interfaces.ISessionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sessions")
@AllArgsConstructor
public class SessionController {
    private ISessionService sessionService;

    @GetMapping("/{id}/tickets")
    public SessionTicketsList getTicketsList(@PathVariable Long id){
        return sessionService.getSessionTicketsData(id);
    }
}