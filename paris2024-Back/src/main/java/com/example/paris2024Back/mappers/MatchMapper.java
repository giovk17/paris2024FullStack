package com.example.paris2024Back.mappers;

import com.example.paris2024Back.domains.Match;
import com.example.paris2024Back.dtos.MatchDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MatchMapper {

    private final TicketMapper ticketMapper;

    public MatchMapper(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }

    public MatchDTO toDto(Match match){
        return new MatchDTO(match.getId(), match.getSportName(), match.getStartDate(), match.getStartHour(), match.getStadiumName(),match.getDiscipline(), match.getOlympicNumOne(), match.getFreeSeats(), this.ticketMapper.toDto(match.getSoldTickets()) , match.getTicketPrice());
    }

    public List<MatchDTO> toDto(List<Match> matches){
        return matches.stream().map(this::toDto).toList();
    }

    public Match toMatch(MatchDTO matchDto){
        return new Match(matchDto.getId(), matchDto.getSportName(), matchDto.getStartDate(), matchDto.getStartHour(), matchDto.getStadiumName(),matchDto.getDiscipline(), matchDto.getOlympicNumOne(), matchDto.getFreeSeats(),this.ticketMapper.toTicket(matchDto.getSoldTickets()) , matchDto.getTicketPrice());
    }

    public List<Match> toMatch(List<MatchDTO> matchDTOS){
        return matchDTOS.stream().map(this::toMatch).toList();
    }
}
