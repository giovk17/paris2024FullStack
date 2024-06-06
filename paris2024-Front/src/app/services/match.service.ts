import { Injectable } from '@angular/core';
import { DataService } from './data.service';
import { MatchDTO, Sports } from '../interfaces/matchDTO';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class MatchService {
  matchesObserver = new BehaviorSubject<MatchDTO[]>([]);
  $matches = this.matchesObserver.asObservable();
  matches: MatchDTO[] = [];

  singleMatchObserver = new BehaviorSubject<MatchDTO>(null);
  $singleMatch = this.singleMatchObserver.asObservable();

  constructor(private data: DataService) {
    this.getAllMatches();
  }

  public getAllMatches() {
    this.data.makeHttpRequest<MatchDTO[]>('get', 'match').subscribe({
      next: (res) => {
        this.matches = res;
        this.matches.forEach((match) => {
          match.freeSeats = match.freeSeats - match.soldTickets.length;
          this.matchesObserver.next(this.matches);
        });
      },
    });
  }

  public getMatchById(id: number) {
    this.data.makeHttpRequest<MatchDTO>('get', `match/${id}`).subscribe({
      next: (match) => {
        match.freeSeats = match.freeSeats - match.soldTickets.length;
        this.singleMatchObserver.next(match);
      },
    });
  }

  public getMatchBySport(sport: Sports) {
    return this.data.makeHttpRequest<MatchDTO[]>(
      'get',
      `match/sports/${sport}`
    );
  }

  public createMatch(match: MatchDTO) {
    return this.data.makeHttpRequest('post', 'match/create');
  }

  public deleteMatch(id: number) {
    return this.data.makeHttpRequest('delete', `match/delete/${id}`);
  }
}
