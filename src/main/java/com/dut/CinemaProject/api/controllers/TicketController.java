package com.dut.CinemaProject.api.controllers;

import com.dut.CinemaProject.bll.interfaces.ITicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ticket")
public class TicketController {

    private ITicketService ticketService;

    public TicketController(ITicketService ticketService){
        this.ticketService = ticketService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long id){
        try{
            var ticket = ticketService.getTicketById(id);
            if(ticket.isPresent())
                ticketService.deleteTicket(id);
            return ResponseEntity.noContent().build();
        }
        catch(ArithmeticException ae){
            return ResponseEntity.status(500).build();
        }
    }

}
