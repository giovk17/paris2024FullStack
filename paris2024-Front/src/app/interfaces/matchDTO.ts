import { TicketDTO } from './ticketDTO';

export interface MatchDTO {
  id: number;
  sportName: Sports;
  startDate: string;
  startHour: number;
  stadiumName: string;
  discipline: string[];
  olympicNumOne: number;
  olympicNumTwo: number;
  freeSeats: number;
  soldTickets: TicketDTO[];
  ticketPrice: number;
}

export enum Sports {
  SOCCER = 'SOCCER',
  BASKETBALL = 'BASKETBALL',
  POWERLIFTING = 'POWERLIFTING',
  WEIGHTLIFTING = 'WEIGHTLIFTING',
  CYCLING = 'CYCLING',
  RUNNING = 'RUNNING',
  SWIMMING = 'SWIMMING',
}
