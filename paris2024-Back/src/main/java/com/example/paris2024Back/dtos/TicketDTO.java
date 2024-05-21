package com.example.paris2024Back.dtos;

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
    private Long user;


    @NotNull(message = "Match id is required")
    private Long match;
}
