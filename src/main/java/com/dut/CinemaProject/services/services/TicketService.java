package com.dut.CinemaProject.services.services;

import com.dut.CinemaProject.dao.domain.Ticket;
import com.dut.CinemaProject.dao.domain.Session;
import com.dut.CinemaProject.dao.domain.User;
import com.dut.CinemaProject.dao.repos.SessionRepository;
import com.dut.CinemaProject.dao.repos.TicketRepository;
import com.dut.CinemaProject.dao.repos.UserRepository;
import com.dut.CinemaProject.dto.Ticket.PurchaseTicketDto;
import com.dut.CinemaProject.dto.Ticket.TicketDto;
import com.dut.CinemaProject.exceptions.ItemNotFoundException;
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
        User user = userRepository.findById(userId).orElseThrow(ItemNotFoundException::new);
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
    public Long purchaseTicket(PurchaseTicketDto purchaseTicketDto) {
        Session session = sessionRepository.findById(purchaseTicketDto.getSessionId())
                .orElseThrow(ItemNotFoundException::new);
        Integer ticketsCount = ticketRepository.countTicketsBySessionId(purchaseTicketDto.getSessionId());

        if(session.getHall().getRowsAmount() < purchaseTicketDto.getRow())
            throw new UnsupportedOperationException("Row out of bounce");

        if(session.getHall().getPlaces() < purchaseTicketDto.getPlace())
            throw new UnsupportedOperationException("Place out of bounce");

        User user = userRepository.findById(purchaseTicketDto.getUserId().orElseThrow(IllegalStateException::new))
                .orElseThrow(ItemNotFoundException::new);

        if(ticketsCount == (session.getHall().getRowsAmount() * session.getHall().getPlaces()))
            throw new UnsupportedOperationException("All tickets are sold out");

        Optional<Ticket> existingTicket = ticketRepository.getTicketByDetails(purchaseTicketDto.getPlace(),
                purchaseTicketDto.getRow(), purchaseTicketDto.getSessionId());

        if(existingTicket.isPresent())
            throw new UnsupportedOperationException("Requested ticket is sold out");

        Ticket ticket = new Ticket();
        ticket.setCustomer(user);
        ticket.setSession(session);
        ticket.setRow(purchaseTicketDto.getRow());
        ticket.setPlace(purchaseTicketDto.getPlace());

        return ticketRepository.save(ticket).getId();
    }
}
