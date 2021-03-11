package com.dut.CinemaProject.services.services;

import com.dut.CinemaProject.dao.domain.Ticket;
import com.dut.CinemaProject.dao.domain.Session;
import com.dut.CinemaProject.dao.domain.User;
import com.dut.CinemaProject.dao.repos.SessionRepository;
import com.dut.CinemaProject.dao.repos.TicketRepository;
import com.dut.CinemaProject.dao.repos.UserRepository;
import com.dut.CinemaProject.dto.Ticket.PurchaseTicket;
import com.dut.CinemaProject.dto.Ticket.TicketDto;
import com.dut.CinemaProject.exceptions.ItemNotFoundException;
import com.dut.CinemaProject.exceptions.ValidationException;
import com.dut.CinemaProject.services.interfaces.ITicketService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TicketService implements ITicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;


    @Override
    public List<TicketDto> getUsersTickets(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ItemNotFoundException("User not found"));
        return ticketRepository.findTicketsByCustomer(user).stream().map(TicketDto::new).collect(Collectors.toList());
    }

    @Override
    public TicketDto getTicketById(Long id) {
        return new TicketDto(ticketRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Ticket not found")));
    }

    @Override
    public void deleteTicket(Long id) {
        Ticket t = ticketRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Ticket not found"));

        ticketRepository.delete(t);
    }

    @Override
    public Long purchaseTicket(PurchaseTicket purchaseTicket) {
        Session session = sessionRepository.findById(purchaseTicket.getSessionId())
                .orElseThrow(ItemNotFoundException::new);

        if(session.getHall().getRowsAmount() < purchaseTicket.getRow())
            throw new ValidationException("Row out of bounce");

        if(session.getHall().getPlaces() < purchaseTicket.getPlace())
            throw new ValidationException("Place out of bounce");

        if(ticketRepository.countTicketsByUserId(purchaseTicket.getUserId()) >= 10)
            throw new ValidationException("User can't buy tickets more than 10!");

        User user = userRepository.findById(purchaseTicket.getUserId())
                .orElseThrow(ItemNotFoundException::new);

        Optional<Ticket> existingTicket = ticketRepository.getTicketByDetails(purchaseTicket.getPlace(),
                purchaseTicket.getRow(), purchaseTicket.getSessionId());

        if(existingTicket.isPresent())
            throw new ValidationException("Requested ticket is sold out");

        Ticket ticket = new Ticket();
        ticket.setCustomer(user);
        ticket.setSession(session);
        ticket.setRow(purchaseTicket.getRow());
        ticket.setPlace(purchaseTicket.getPlace());

        return ticketRepository.save(ticket).getId();
    }
}
