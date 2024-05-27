import { Injectable } from '@angular/core';
import { UserDTO } from '../interfaces/user';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  currentUser: UserDTO;

  public setUser(user: UserDTO) {
    this.currentUser = user;
  }

  public getUser() {
    return this.currentUser;
  }

  public clearUser() {
    this.currentUser = null;
  }
}
