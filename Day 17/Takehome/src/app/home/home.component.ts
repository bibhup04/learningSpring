import { Component } from '@angular/core';

import { Tennis } from '../models/tennis'; 

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  tennis: Tennis;
  gameWon: boolean = false;
  player1Name: string = 'player1'; 
  player2Name: string = 'player2';
  winnerName: string = '';

  constructor() {
    this.tennis = new Tennis('Player 1', 'Player 2');
    console.log(this.tennis.toString());
  }


  playerWon(playerName: string) {
     this.gameWon = this.tennis.updateScore(playerName);
    console.log(this.tennis.toString());

    if (this.gameWon) {
      if(playerName === 'Player 1'){
        this.winnerName = this.player1Name;
      }
      else{
        this.winnerName = this.player2Name;
      }
       
      console.log(`Game won by ${playerName}`);
    }
  }

  restartGame() {
    window.location.reload();
  }
  
}
