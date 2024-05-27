import { TicketDTO } from './ticket';

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
  SOCCER = 'Soccer',
  BASKETBALL = 'Basketball',
  POWERLIFTING = 'Powerlifting',
  WEIGHTLIFTING = 'Weightlifting',
  CYCLING = 'Cycling',
  RUNNING = 'Running',
  SWIMMING = 'Swimming',
}
