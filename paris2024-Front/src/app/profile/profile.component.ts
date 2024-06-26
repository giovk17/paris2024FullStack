import { Component, OnDestroy, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { UserDTO } from '../interfaces/userDTO';
import { NgForm } from '@angular/forms';
import { Subscription } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css',
})
export class ProfileComponent implements OnInit, OnDestroy {
  currentUser: UserDTO | null;
  showMode = true;
  pwShowMode = false;
  passwordArr: string[];
  hiddenPassword: string;

  private authSub: Subscription;

  constructor(private auth: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.authSub = this.auth.$user.subscribe({
      next: (user) => {
        this.currentUser = user;
        this.passwordArr = this.currentUser.password.split('');
        this.hiddenPassword = Array.from(this.passwordArr, () => '*').join('');
      },
      error: (err) => {
        console.error('User not found', err);
      },
    });
  }

  showPassword() {
    if (this.pwShowMode) {
      this.hiddenPassword = this.currentUser.password;
    } else {
      this.hiddenPassword = Array.from(this.passwordArr, () => '*').join('');
    }
    this.pwShowMode = !this.pwShowMode;
  }

  toTickets() {
    this.router.navigate([`main/profile/tickets`]);
  }

  changeDetails(form: NgForm) {}

  ngOnDestroy(): void {
    this.authSub.unsubscribe();
  }
}
