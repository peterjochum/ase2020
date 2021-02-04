import { GameService } from '../app/services/game.service';
import { UserResourceService } from '../app/integration';

export const environment = {
  production: true,
  gameService: GameService,
  userService: UserResourceService
};
