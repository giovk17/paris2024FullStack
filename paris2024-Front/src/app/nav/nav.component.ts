import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrl: './nav.component.css',
})
export class NavComponent {
  constructor(private router: Router, private auth: AuthService) {}

  toProfile() {
    this.router.navigate(['/main/profile']);
  }

  logout() {
    this.auth.clearUser();
    this.router.navigate(['/']);
  }
}
