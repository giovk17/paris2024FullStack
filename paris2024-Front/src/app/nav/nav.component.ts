import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { UserDTO } from '../interfaces/userDTO';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrl: './nav.component.css',
})
export class NavComponent {
  currentUser: UserDTO;
  constructor(private router: Router, private auth: AuthService) {
    this.currentUser = this.auth.getUser();
  }

  toSports() {
    this.router.navigate(['/main/sports']);
  }

  toProfile() {
    this.router.navigate(['/main/profile']);
  }

  logout() {
    this.auth.clearUser();
    this.router.navigate(['/']);
  }
}
