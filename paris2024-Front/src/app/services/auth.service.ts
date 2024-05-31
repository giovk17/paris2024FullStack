import { Injectable } from '@angular/core';
import { UserDTO } from '../interfaces/userDTO';
import { DataService } from './data.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  currentUser: UserDTO | null = null;

  constructor(private data: DataService) {}

  // USER RELATED AUTH METHODS

  public setUser(user: UserDTO) {
    this.currentUser = user;
  }

  public getUser() {
    return this.currentUser;
  }

  public clearUser() {
    this.currentUser = null;
  }

  public isLoggedIn(): boolean {
    return this.currentUser !== null;
  }

  // USER RELATED HTTP METHODS

  public getAllUsers(): Observable<UserDTO[]> {
    return this.data.makeHttpRequest<UserDTO[]>('get', 'user');
  }

  public createUser(user: UserDTO) {
    return this.data.makeHttpRequest('post', 'user/create');
  }

  public deleteUser(id: number) {
    return this.data.makeHttpRequest('delete', `user/delete/${id}`);
  }
}
