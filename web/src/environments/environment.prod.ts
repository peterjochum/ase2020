import { GameResourceService, UserResourceService } from '../app/integration';

export const environment = {
  production: true,
  gameService: GameResourceService,
  userService: UserResourceService
};
