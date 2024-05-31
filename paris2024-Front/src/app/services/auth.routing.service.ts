import { Injectable } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class AuthRoutingService {
  constructor(private router: Router, private authService: AuthService) {
    this.router.events.subscribe({
      next: (event) => {
        if (event instanceof NavigationEnd) {
          if (event.urlAfterRedirects === '/login') {
            if (this.authService.isLoggedIn()) {
              this.router.navigate(['/main']);
            }
          }
        }
      },
    });
  }
}
