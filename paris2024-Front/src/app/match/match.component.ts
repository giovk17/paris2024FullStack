import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { AuthService } from '../services/auth.service';
import { UserDTO, userRole } from '../interfaces/userDTO';
import { ActivatedRoute } from '@angular/router';
import { MatchService } from '../services/match.service';
import { MatchDTO, Sports } from '../interfaces/matchDTO';

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
export class MatchComponent implements OnInit {
  public dataSource = new MatTableDataSource<any>();
  public displayedColumns = [
    'sport',
    'date',
    'stadium',
    'discipline',
    'seats',
    'price',
  ];
  public currentUser: UserDTO;

  constructor(
    private auth: AuthService,
    private route: ActivatedRoute,
    private matchService: MatchService
  ) {
    this.currentUser = this.auth.getUser();
  }

  ngOnInit(): void {
    if (this.isAdmin()) {
      this.displayedColumns.push('sold');
    }

    this.matchService.getAllMatches().subscribe({
      next: (matches) => {
        const currentSport = this.route.snapshot.paramMap.get(
          'sport'
        ) as Sports;
        const rows = [];
        this.filterMatches(matches, currentSport).forEach((match) => {
          rows.push(this.createRows(match));
        });
        this.dataSource.data = rows;
      },
    });
  }

  public isAdmin() {
    return this.currentUser.userRole == userRole.Admin;
  }

  private filterMatches(matches: MatchDTO[], sportName: Sports) {
    return matches.filter((match) => match.sportName == sportName);
  }

  private createRows(filteredArr: MatchDTO) {
    return {
      sport: filteredArr.sportName,
      date: `${filteredArr.startDate} at ${filteredArr.startHour}h`,
      stadium: filteredArr.stadiumName,
      discipline: filteredArr.discipline ? ['N/A'] : filteredArr.discipline,
      seats: filteredArr.freeSeats,
      price: filteredArr.ticketPrice,
      sold: filteredArr.soldTickets.length,
    };
  }
}
