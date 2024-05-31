import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthComponent } from './auth/auth.component';
import { MainComponent } from './main/main.component';
import { AuthGuard } from './guards/authGuard';
import { SportsComponent } from './sports/sports.component';
import { ProfileComponent } from './profile/profile.component';
import { MatchComponent } from './match/match.component';

const routes: Routes = [
  { path: 'login', component: AuthComponent },
  {
    path: 'main',
    component: MainComponent,
    canActivate: [AuthGuard],
    children: [
      { path: 'sports', component: SportsComponent },
      { path: 'sports/:sport', component: MatchComponent },
      { path: 'profile', component: ProfileComponent },
      { path: '', redirectTo: 'sports', pathMatch: 'full' },
    ],
  },
  { path: '**', redirectTo: 'login', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
