package com.example.paris2024Back.mappers;

import com.example.paris2024Back.domains.Ticket;
import com.example.paris2024Back.dtos.TicketDTO;
import com.example.paris2024Back.repositories.MatchRepository;
import com.example.paris2024Back.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TicketMapper {

    private final UserRepository userRepository;
    private final MatchRepository matchRepository;


    public TicketMapper(UserRepository userRepository, MatchRepository matchRepository) {
        this.userRepository = userRepository;
        this.matchRepository = matchRepository;
    }

    public TicketDTO toDto(Ticket ticket){
        return new TicketDTO(ticket.getId(),ticket.getUser().getId(), ticket.getMatch().getId() );
    }

    public List<TicketDTO> toDto(List<Ticket> tickets){
        return tickets.stream().map(this::toDto).toList();
    }

    public Ticket toTicket(TicketDTO ticketDTO){
        return new Ticket(ticketDTO.getId(), this.userRepository.findById(ticketDTO.getUser()).orElseThrow(() -> new NullPointerException("User not found")), this.matchRepository.findById(ticketDTO.getMatch()).orElseThrow(() -> new NullPointerException("Match not found")));
    }

    public List<Ticket> toTicket(List<TicketDTO> ticketDTOS){
        return ticketDTOS.stream().map(this::toTicket).toList();
    }


}
