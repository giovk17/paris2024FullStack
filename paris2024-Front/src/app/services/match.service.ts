import { Injectable } from '@angular/core';
import { DataService } from './data.service';
import { MatchDTO, Sports } from '../interfaces/matchDTO';

@Injectable({
  providedIn: 'root',
})
export class MatchService {
  constructor(private data: DataService) {}

  public getAllMatches() {
    return this.data.makeHttpRequest<MatchDTO[]>('get', 'match');
  }

  public getMatchBySport(sport: Sports) {
    return this.data.makeHttpRequest<MatchDTO[]>('get', `match/${sport}`);
  }

  public createMatch(match: MatchDTO) {
    return this.data.makeHttpRequest('post', 'match/create');
  }

  public deleteMatch(id: number) {
    return this.data.makeHttpRequest('delete', `match/delete/${id}`);
  }
}
