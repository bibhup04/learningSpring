export class Player {
    
    public name: string;
    public servesWon: number;
    public gamesWon: number;
    public setsWon: number;
  
    public scores: string[] = ["Love", "15", "30", "40", "Adv", "Adv", "Game"];
  
    constructor(name: string) {
      this.name = name;
      this.servesWon = 0;
      this.gamesWon = 0;
      this.setsWon = 0;
    }
  
    getName(): string {
      return this.name;
    }
  
    getScore(): string {
      return this.scores[this.servesWon];
    }
  
    getServesWon(): number {
      return this.servesWon;
    }
  
    getGamesWon(): number {
      return this.gamesWon;
    }
  
    getSetsWon(): number {
      return this.setsWon;
    }
  
    setServesWon(score: number): void {
      this.servesWon = score;
    }
  
    // setServesWon(score: string): void {
    //   this.servesWon = this.scores.indexOf(score);
    // }
  
    setGamesWon(gamesWon: number): void {
      this.gamesWon = gamesWon;
    }
  
    setSetsWon(setsWon: number): void {
      this.setsWon = setsWon;
    }
  }
  