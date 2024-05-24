package com.example.paris2024Back.services;

import com.example.paris2024Back.domains.Match;
import com.example.paris2024Back.domains.Ticket;
import com.example.paris2024Back.domains.User;
import com.example.paris2024Back.dtos.TicketDTO;
import com.example.paris2024Back.mappers.TicketMapper;
import com.example.paris2024Back.repositories.MatchRepository;
import com.example.paris2024Back.repositories.TicketRepo;
import com.example.paris2024Back.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class TicketService {

    private final TicketRepo ticketRepo;
    private final MatchRepository matchRepository;
    private final UserRepository userRepository;
    private final TicketMapper ticketMapper;

    public List<TicketDTO> getAllTickets(){
        return this.ticketMapper.toDto(this.ticketRepo.findAll());
    }

    public TicketDTO findByMatchIdAndUserId(Long matchId, Long userId){
        TicketDTO selected =  this.ticketMapper.toDto(this.ticketRepo.findByMatchIdAndUserId(matchId, userId));
        try {
            return selected;
        }catch (NullPointerException e){
            throw new NullPointerException("Ticket not found");
        }
    }

    public List<TicketDTO> findAllByMatchIdAndUserId(Long matchId, Long userId){
        List<TicketDTO> selected = this.ticketMapper.toDto(this.ticketRepo.findAllByMatchIdAndUserId(matchId, userId));
        try {
            return selected;
        }catch (NullPointerException e){
            throw new NullPointerException("Tickets not found");
        }
    }

    public void createTicket(TicketDTO ticketDTO){
        User user = this.userRepository.findById(ticketDTO.getUser()).orElseThrow(() -> new NullPointerException("User not found") );
        Match match = this.matchRepository.findById(ticketDTO.getMatch()).orElseThrow(() -> new NullPointerException("Match not found"));
        Long ticketId = ticketDTO.getId();
        Ticket ticket = new Ticket(ticketId, user, match);

        this.ticketRepo.save(ticket);
        user.purchaseTicket(ticket);
        match.sellTicket(ticket);


    }
}
