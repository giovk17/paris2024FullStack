import { TicketDTO } from './ticket';

export interface UserDTO {
  id: number;
  name: string;
  username: string;
  password: string;
  role: userRole;
  ownedTickets: TicketDTO[];
}
export enum userRole {
  USER = 'User',
  Admin = 'Admin',
}
