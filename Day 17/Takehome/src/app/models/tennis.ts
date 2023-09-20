import { Player } from './player'; 
import { Games } from './game';

export class Tennis {
  p1: Player;
  p2: Player;
  game: Games;
  player1: string;
  player2: string;

  constructor(name1: string, name2: string) {
    this.p1 = new Player(name1);
    this.p2 = new Player(name2);
    this.player1 = name1;
    this.player2 = name2;
    this.game = new Games(this.p1, this.p2);
  }

  updateScore(name: string): boolean {
    if (name === this.player1) {
      const serves = this.p1.getServesWon();
      if (serves < 5) {
        this.p1.setServesWon(serves + 1);
      }
    } else {
      const serves = this.p2.getServesWon();
      if (serves < 5) {
        this.p2.setServesWon(serves + 1);
      }
    }
    
    if (this.compareScores()) {
      return this.game.updateGame();
    }
    
    return false;
  }

  compareScores(): boolean {
    if (this.p1.getServesWon() === 3 && this.p2.getServesWon() === 3) {
      return false;
    }
    
    if (this.p1.getServesWon() === 4 && this.p2.getServesWon() === 4) {
      this.p1.setServesWon(3);
      this.p2.setServesWon(3);
      return false;
    }
    
    if (this.p1.getServesWon() > 3 && this.p1.getServesWon() - this.p2.getServesWon() >= 2) {
      this.p1.setServesWon(6);
      return true;
    }
    
    if (this.p2.getServesWon() > 3 && this.p2.getServesWon() - this.p1.getServesWon() >= 2) {
      this.p2.setServesWon(6);
      return true;
    }
    
    return false;
  }

  toString(): string {
    let result = "Player Name     Score  Game Won  Set Won \n";
    result += `    ${this.p1.getName()}\t\t${this.p1.getScore()} \t  ${this.p1.getGamesWon()}    \t   ${this.p1.getSetsWon()}  \n`;
    result += `    ${this.p2.getName()}\t\t${this.p2.getScore()} \t  ${this.p2.getGamesWon()}    \t   ${this.p2.getSetsWon()}  \n`;
    result += this.p1.getSetsWon() >= 2 ? ` Winner is ${this.p1.getName()}` : this.p2.getSetsWon() >= 2 ? ` Winner is ${this.p2.getName()}` : "";
    return result;
  }
}


