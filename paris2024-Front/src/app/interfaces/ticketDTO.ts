export interface TicketDTO {
  ticketId: number;
  userId: number;
  matchId: number;
}

export interface CreateTicketDTO {
  userId: number;
  matchId: number;
}
