package com.dut.CinemaProject.services.services;

<<<<<<< HEAD:src/main/java/com/dut/CinemaProject/services/services/TicketService.java
import com.dut.CinemaProject.services.interfaces.ITicketService;
=======
import com.dut.CinemaProject.bll.interfaces.ITicketService;
import com.dut.CinemaProject.dao.domain.Ticket;
import com.dut.CinemaProject.dao.repos.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> 385a38f (add TicketController):src/main/java/com/dut/CinemaProject/bll/services/TicketService.java
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketService implements ITicketService {
    @Autowired
    TicketRepository ticketRepository;

    @Override
    public Optional<Ticket> getTicketById(Long id) {
        return ticketRepository.findById(id);
    }

    @Override
    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }
}
