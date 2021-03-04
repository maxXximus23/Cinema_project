package com.dut.CinemaProject.services.services;

import com.dut.CinemaProject.dao.domain.User;
import com.dut.CinemaProject.dao.repos.TicketRepository;
import com.dut.CinemaProject.dao.repos.UserRepository;
import com.dut.CinemaProject.dto.Ticket.TicketDto;
import com.dut.CinemaProject.exceptions.ItemNotFoundException;
import com.dut.CinemaProject.services.interfaces.ITicketService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TicketService implements ITicketService {
    private TicketRepository ticketRepository;
    private UserRepository userRepository;


    @Override
    public List<TicketDto> getUsersTickets(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(ItemNotFoundException::new);
        return ticketRepository.findTicketsByCustomer(user).stream().map(TicketDto::new).collect(Collectors.toList());
    }
}
