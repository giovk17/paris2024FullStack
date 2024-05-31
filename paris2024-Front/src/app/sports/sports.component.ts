import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatchDTO, Sports } from '../interfaces/matchDTO';
import { MatchService } from '../services/match.service';
import { Router } from '@angular/router';

export interface rowData {
  sport: Sports;
  amount: number;
  date: string;
}

@Component({
  selector: 'app-sports',
  templateUrl: './sports.component.html',
  styleUrls: ['./sports.component.css'],
})
export class SportsComponent implements OnInit {
  public dataSource = new MatTableDataSource<rowData>([]);
  public displayedColumns = ['sport', 'amount', 'date'];

  constructor(private matchService: MatchService, private router: Router) {}

  ngOnInit(): void {
    this.matchService.getAllMatches().subscribe({
      next: (matches) => {
        const rows: rowData[] = Object.values(Sports).map((sport) => {
          return this.filterBySports(matches, sport);
        });
        this.dataSource.data = rows;
      },
    });
  }

  public navigateToDetails(sport: Sports) {
    this.router.navigate([`/main/sports/${sport}`]);
  }

  private filterBySports(matches: MatchDTO[], sportName: Sports): rowData {
    let filtered = matches.filter((match) => match.sportName === sportName);
    let amount = filtered.length;
    let now = new Date();

    let closestMatch = filtered.reduce((closest, match) => {
      let matchTime = new Date(
        `${match.startDate}T${String(match.startHour).padStart(2, '0')}:00:00`
      );
      let closestTime = new Date(
        `${closest.startDate}T${String(closest.startHour).padStart(
          2,
          '0'
        )}:00:00`
      );

      return Math.abs(matchTime.getTime() - now.getTime()) <
        Math.abs(closestTime.getTime() - now.getTime())
        ? match
        : closest;
    }, filtered[0]);

    if (closestMatch) {
      let closestDate = closestMatch.startDate;
      let closesthour = closestMatch.startHour;

      return {
        sport: sportName,
        amount: amount,
        date: `${closestDate} at ${closesthour}h`,
      };
    } else {
      return {
        sport: sportName,
        amount: amount,
        date: `N/A`,
      };
    }
  }
}
