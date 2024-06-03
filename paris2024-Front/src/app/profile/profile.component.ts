import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { UserDTO } from '../interfaces/userDTO';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css',
})
export class ProfileComponent {
  currentUser: UserDTO | null;
  showMode = true;
  pwShowMode = false;
  passwordArr: string[];
  hiddenPassword: string;

  constructor(private auth: AuthService) {
    this.currentUser = auth.getUser();
    this.passwordArr = this.currentUser.password.split('');
    this.hiddenPassword = Array.from(this.passwordArr, () => '*').join('');
  }

  showPassword() {
    if (this.pwShowMode) {
      this.hiddenPassword = this.currentUser.password;
    } else {
      this.hiddenPassword = Array.from(this.passwordArr, () => '*').join('');
    }
    this.pwShowMode = !this.pwShowMode;
  }

  changeDetails(form: NgForm) {}
}
