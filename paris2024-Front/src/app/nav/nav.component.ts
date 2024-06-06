import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { UserDTO } from '../interfaces/userDTO';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrl: './nav.component.css',
})
export class NavComponent implements OnInit {
  currentUser: UserDTO;
  constructor(private router: Router, private auth: AuthService) {}

  ngOnInit(): void {
    this.auth.$user.subscribe({
      next: (user) => {
        this.currentUser = user;
      },
      error: (err) => {
        console.log('User not found', err);
      },
    });
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
