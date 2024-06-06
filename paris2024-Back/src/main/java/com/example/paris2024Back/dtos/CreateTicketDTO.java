package com.example.paris2024Back.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class CreateTicketDTO {

    @NotNull(message = "User id is required")
    private Long userId;


    @NotNull(message = "Match id is required")
    private Long matchId;
}
