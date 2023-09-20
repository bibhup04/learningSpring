import { Player } from './player'; // Import the Player class (assuming it's in a separate file)

export class Set {
  p1: Player;
  p2: Player;

  constructor(p1: Player, p2: Player) {
    this.p1 = p1;
    this.p2 = p2;
  }

  updateSet(): boolean {
    if (this.p1.getGamesWon() > this.p2.getGamesWon()) {
      this.p1.setSetsWon(this.p1.getSetsWon() + 1);
    } else {
      this.p2.setSetsWon(this.p2.getSetsWon() + 1);
    }
    this.p1.setGamesWon(0);
    this.p2.setGamesWon(0);

    if (this.p1.getSetsWon() === 2 || this.p2.getSetsWon() === 2) {
      return true;
    }
    return false;
  }
}
