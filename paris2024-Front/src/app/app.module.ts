import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { AuthComponent } from './auth/auth.component';

import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { MainComponent } from './main/main.component';
import { NavComponent } from './nav/nav.component';
import { SportsComponent } from './sports/sports.component';
import { ProfileComponent } from './profile/profile.component';
import { AuthRoutingService } from './services/auth.routing.service';
import { MatchComponent } from './match/match.component';

@NgModule({
  declarations: [
    AppComponent,
    AuthComponent,
    MainComponent,
    NavComponent,
    SportsComponent,
    ProfileComponent,
    MatchComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatTableModule,
  ],
  providers: [AuthRoutingService],
  bootstrap: [AppComponent],
})
export class AppModule {}
