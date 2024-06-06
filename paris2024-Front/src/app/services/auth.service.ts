import { Injectable } from '@angular/core';
import { UserDTO, userRole } from '../interfaces/userDTO';
import { DataService } from './data.service';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  UserObserver = new BehaviorSubject<UserDTO>(null);
  $user = this.UserObserver.asObservable();
  currentUser: UserDTO | null = null;

  constructor(private data: DataService) {}

  // USER RELATED AUTH METHODS

  public setUser(user: UserDTO) {
    this.currentUser = user;
    this.UserObserver.next(this.currentUser);
  }

  public isAdmin() {
    return this.currentUser.userRole == userRole.Admin;
  }

  public clearUser() {
    this.currentUser = null;
  }

  public isLoggedIn(): boolean {
    return this.currentUser !== null;
  }

  // USER RELATED HTTP METHODS

  public getUserById(id: number) {
    this.data.makeHttpRequest<UserDTO>('get', `user/${id}`).subscribe({
      next: (user) => {
        this.currentUser = user;
        this.UserObserver.next(this.currentUser);
      },
    });
  }

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
