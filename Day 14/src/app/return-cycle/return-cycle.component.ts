import { Component } from '@angular/core';
import { ApiserviceService } from '../service';
import { HttpClient } from '@angular/common/http';
import { cycle, cycleList } from '../cycle-interface';


@Component({
  selector: 'app-return-cycle',
  templateUrl: './return-cycle.component.html',
  styleUrls: ['../cycle-list/cycle-list.component.css']
})
export class ReturnCycleComponent {
  title = 'cycle-return';
  newdata: cycleList[] = [];

  constructor(
    private _apiserver: ApiserviceService,
    private http: HttpClient,
  ) {}

  ngOnInit() {
    this._apiserver.getData().subscribe(
      (res) => {
          this.newdata = res.filter(item => item.numBorrowed > 0);
          console.log('Success: Response from API:', this.newdata);
       },
      (error) => {
        console.error('Error: Failed to fetch data from API:', error);
      }
    );
  }


  returnCycle(cycleId: number) {
    const cycleReturn = {
      id: cycleId,
      count: 1,
    };

    this._apiserver.returnCycle(cycleReturn)
      .subscribe(
        (response) => {
          console.log('Cycle return successfully:', response);
          this.ngOnInit();
        },
        (error) => {
          console.error('Error in cycle return:', error);
        }
      );
    console.log(`Returning cycle with ID: ${cycleId}`);
  }
}
