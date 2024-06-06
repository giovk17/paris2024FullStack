import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MatchService } from '../services/match.service';
import { MatchDTO } from '../interfaces/matchDTO';
import { Location } from '@angular/common';
import { AuthService } from '../services/auth.service';
import { UserDTO, userRole } from '../interfaces/userDTO';
import { CreateTicketDTO } from '../interfaces/ticketDTO';
import { DataService } from '../services/data.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrl: './detail.component.css',
})
export class DetailComponent implements OnInit, OnDestroy {
  isAdmin: boolean;
  currentMatch: MatchDTO;
  currentUser: UserDTO;
  ownedTickets = [];

  authSub: Subscription;
  matchSub: Subscription;

  constructor(
    private route: ActivatedRoute,
    private matchService: MatchService,
    private location: Location,
    private auth: AuthService,
    private dataService: DataService
  ) {}

  ngOnInit(): void {
    this.authSub = this.auth.$user.subscribe({
      next: (user) => {
        this.currentUser = user;
        let currentId = +this.route.snapshot.paramMap.get('id');
        this.matchService.getMatchById(currentId);
        this.isAdmin = this.auth.isAdmin();
        this.matchSub = this.matchService.$singleMatch.subscribe({
          next: (match) => {
            if (match) {
              this.currentMatch = match;
              this.refreshTickets();
            } else {
              this.location.back();
            }
          },
        });
      },
      error: (err) => {
        console.error('No user found', err);
      },
    });
  }

  public refreshTickets() {
    this.ownedTickets = this.currentUser.ownedTickets.filter(
      (ticket) => ticket.matchId == this.currentMatch.id
    );
  }

  public buyTicket() {
    if (this.currentMatch.freeSeats <= 0) {
      alert('Sold out!');
      return;
    }
    const ticket: CreateTicketDTO = {
      userId: this.currentUser.id,
      matchId: this.currentMatch.id,
    };

    this.dataService
      .makeHttpRequest('post', 'ticket/create', ticket)
      .subscribe({
        next: () => {
          this.matchService.getMatchById(this.currentMatch.id);
          this.auth.getUserById(this.currentUser.id);
          this.refreshTickets();
        },
        error: (err) => {
          console.error('Error creating ticket:', err);
        },
      });
  }

  ngOnDestroy(): void {
    this.authSub.unsubscribe();
    this.matchSub.unsubscribe();
  }
}
