import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { DataService } from '../services/data.service';
import { AuthService } from '../services/auth.service';
import { UserDTO } from '../interfaces/userDTO';
import { Router } from '@angular/router';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrl: './auth.component.css',
})
export class AuthComponent {
  constructor(
    private data: DataService,
    private auth: AuthService,
    private router: Router
  ) {}

  private login(username: string, password: string) {
    this.data
      .makeHttpRequest<UserDTO>('get', `user/${username}/${password}`)
      .subscribe({
        next: (user) => {
          this.auth.setUser(user);
          this.router.navigate(['main']);
        },
        error: (errorMsg) => {
          console.error(errorMsg);
        },
      });
  }

  public submit(form: NgForm) {
    if (form.valid) {
      this.login(form.value.userName.trim(), form.value.password.trim());
    }
  }
}
