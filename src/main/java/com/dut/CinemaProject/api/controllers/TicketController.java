package com.dut.CinemaProject.api.controllers;

import com.dut.CinemaProject.services.interfaces.ITicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ticket")
@AllArgsConstructor
public class TicketController {
    private final ITicketService ticketService;

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelBooking(@PathVariable Long id){
        ticketService.deleteTicket(id);
    }

}
