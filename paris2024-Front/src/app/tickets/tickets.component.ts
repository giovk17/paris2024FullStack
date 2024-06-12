import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { UserDTO } from '../interfaces/userDTO';
import { MatTableDataSource } from '@angular/material/table';
import { MatchService } from '../services/match.service';
import { MatchDTO } from '../interfaces/matchDTO';

@Component({
  selector: 'app-tickets',
  templateUrl: './tickets.component.html',
  styleUrl: './tickets.component.css',
})
export class TicketsComponent implements OnInit {
  private currentUser: UserDTO;
  private userMatches = [];
  public dataSource = new MatTableDataSource<any>();
  public displayedColumns = ['matchId', 'sport', 'price', 'amount', 'toMatch'];
  constructor(private auth: AuthService, private matchService: MatchService) {}

  ngOnInit(): void {
    this.auth.$user.subscribe({
      next: (user) => {
        this.currentUser = user;
      },
    });

    this.matchService.$matches.subscribe({
      next: (matches) => {
        for (let i = 0; i < matches.length; i++) {
          for (let j = 0; j < this.currentUser.ownedTickets.length; j++) {
            if (matches[i].id === this.currentUser.ownedTickets[j].matchId) {
              if (!this.userMatches.includes(matches[i])) {
                this.userMatches.push(matches[i]);
                this.userMatches[this.userMatches.length - 1].amount = 1;
              } else {
                this.userMatches.find((match) => match === matches[i]).amount++;
              }
            }
          }
        }
      },
    });
  }
}
