package com.example.paris2024Back.mappers;

import com.example.paris2024Back.domains.Ticket;
import com.example.paris2024Back.dtos.TicketDTO;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TicketMapper {
    public TicketDTO toDto(Ticket ticket){
        return new TicketDTO(ticket.getId(),ticket.getUser(), ticket.getMatch() );
    }

    public List<TicketDTO> toDto(List<Ticket> tickets){
        return tickets.stream().map(this::toDto).toList();
    }

    public Ticket toTicket(TicketDTO ticketDTO){
        return new Ticket(ticketDTO.getId(), ticketDTO.getUser(), ticketDTO.getMatch());
    }

    public List<Ticket> toTicket(List<TicketDTO> ticketDTOS){
        return ticketDTOS.stream().map(this::toTicket).toList();
    }
}
