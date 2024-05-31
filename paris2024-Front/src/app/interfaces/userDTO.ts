import { TicketDTO } from './ticketDTO';

export interface UserDTO {
  id: number;
  name: string;
  username: string;
  password: string;
  userRole: userRole;
  ownedTickets: TicketDTO[];
}
export enum userRole {
  USER = 'USER',
  Admin = 'ADMIN',
}
