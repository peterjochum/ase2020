/**
 * Game encapsulates the properties of a video game.
 */
export interface Game {
  /** primary key */
  id: number;

  /** name or title of the game */
  name: string;

  /** url to the cover image */
  image: string;

  /** text describes what the game is about */
  text: string;

  /** year of release */
  year: number;
}
