import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent  } from './login/login.component';
import { GamesListComponent } from './games-list/games-list.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { RegisterComponent } from './register/register.component';
import { GameDetailComponent } from './game-detail/game-detail.component';
import { ProfileComponent } from './profile/profile.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent},
  { path: 'register', component: RegisterComponent},
  { path: 'games', component: GamesListComponent},
  { path: 'gameDetail/:id', component: GameDetailComponent },
  { path: 'profile/:userId', component: ProfileComponent},
  { path: 'welcome', component: WelcomeComponent},
  // default
  { path: '**', component: WelcomeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
