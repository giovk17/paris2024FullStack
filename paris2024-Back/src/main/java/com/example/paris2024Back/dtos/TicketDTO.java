package com.example.paris2024Back.dtos;

import com.example.paris2024Back.domains.Match;
import com.example.paris2024Back.domains.User;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TicketDTO {

    private Long id;


    @NotNull(message = "User id is required")
    private User user;


    @NotNull(message = "Match id is required")
    private Match match;
}
