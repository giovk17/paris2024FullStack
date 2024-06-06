import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { AuthService } from '../services/auth.service';
import { UserDTO, userRole } from '../interfaces/userDTO';
import { ActivatedRoute, Router } from '@angular/router';
import { MatchService } from '../services/match.service';
import { MatchDTO, Sports } from '../interfaces/matchDTO';
import { Subscription } from 'rxjs';

export interface MatchRowData {
  sport: Sports;
  date: string;
  stadium: string;
  discipline: string[];
  seats: number;
  price: number;
  sold: number;
}

@Component({
  selector: 'app-match',
  templateUrl: './match.component.html',
  styleUrl: './match.component.css',
})
export class MatchComponent implements OnInit, OnDestroy {
  public dataSource = new MatTableDataSource<any>();
  public displayedColumns = ['date', 'stadium', 'discipline', 'seats', 'price'];
  public currentUser: UserDTO;
  public sportTitle: string;
  public matches: MatchDTO[] = [];

  private matchSub: Subscription;
  private authSub: Subscription;

  constructor(
    private auth: AuthService,
    private route: ActivatedRoute,
    private router: Router,
    private matchService: MatchService
  ) {}

  ngOnInit(): void {
    this.refreshMatches();
    this.authSub = this.auth.$user.subscribe({
      next: (user) => {
        this.currentUser = user;
      },
      error: () => {
        console.error('No user found');
      },
    });
    if (this.isAdmin()) {
      this.displayedColumns.push('sold');
    }

    this.matchSub = this.matchService.$matches.subscribe({
      next: (matches) => {
        this.matches = matches;
        const currentSport = this.route.snapshot.paramMap.get(
          'sport'
        ) as Sports;
        this.sportTitle = currentSport;
        const rows = [];
        this.filterMatches(this.matches, currentSport).forEach((match) => {
          rows.push(this.createRows(match));
        });
        this.dataSource.data = rows;
      },
    });
  }

  private refreshMatches() {
    this.matchService.getAllMatches();
  }

  public toDetails(row) {
    this.router.navigate([`/main/sports/details/${row.ref.id}`]);
  }

  public isAdmin() {
    return this.currentUser.userRole == userRole.Admin;
  }

  private filterMatches(matches: MatchDTO[], sportName: Sports) {
    return matches.filter((match) => match.sportName == sportName);
  }

  private createRows(filteredArr: MatchDTO) {
    return {
      date: `${filteredArr.startDate} at ${filteredArr.startHour}h`,
      stadium: filteredArr.stadiumName,
      discipline: filteredArr.discipline ? ['N/A'] : filteredArr.discipline,
      seats: filteredArr.freeSeats,
      price: filteredArr.ticketPrice,
      sold: filteredArr.soldTickets.length,
      ref: filteredArr,
    };
  }

  ngOnDestroy(): void {
    this.matchSub.unsubscribe();
    this.authSub.unsubscribe();
  }
}
