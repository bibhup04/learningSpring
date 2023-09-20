import { Player } from './player';
import { Set } from './set'; // Import the Sets class (assuming it's in a separate file)

export class Games {
  p1: Player;
  p2: Player;
  sets: Set;
  private readonly MINIMUM_GAME_WON = 6;

  constructor(p1: Player, p2: Player) {
    this.p1 = p1;
    this.p2 = p2;
    this.sets = new Set(p1, p2);
  }

  updateGame(): boolean {
    if (this.p1.getServesWon() === 6) {
      this.p1.setGamesWon(this.p1.getGamesWon() + 1);
    } else {
      this.p2.setGamesWon(this.p2.getGamesWon() + 1);
    }

    this.p1.setServesWon(0);
    this.p2.setServesWon(0);

    if (this.compareGame()) {
      return this.sets.updateSet();
    }

    return false;
  }

  compareGame(): boolean {
    if (Math.abs(this.p1.getGamesWon() - this.p2.getGamesWon()) >= 2) {
      if (
        this.p1.getGamesWon() >= this.MINIMUM_GAME_WON ||
        this.p2.getGamesWon() >= this.MINIMUM_GAME_WON
      ) {
        return true;
      }
    }
    return false;
  }
}
