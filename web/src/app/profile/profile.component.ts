import { Component, Inject, OnInit } from '@angular/core';
import { GameDTO, GameResourceServiceInterface, UserDTO, UserResourceServiceInterface } from '../integration';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  user?: UserDTO;
  suggestedGames?: GameDTO[];
  collectedGames?: GameDTO[];

  constructor(
    @Inject('IGameService') private gameService: GameResourceServiceInterface,
    @Inject('IUserResourceService') private userService: UserResourceServiceInterface,
    private router: Router,
    private route: ActivatedRoute) {}

  ngOnInit(): void {
    const userId: number = Number(this.route.snapshot.paramMap.get('userId'));
    this.userService.getUserById(userId).subscribe((user) => {
      this.user = user;
    });

    this.gameService.getSuggestedGames(userId).subscribe((games) => {
      this.suggestedGames = games;
    });

    this.gameService.getGamecollection(userId).subscribe((gc) => {
      console.log(gc);
      this.collectedGames = gc.games;
    })
  }

}
