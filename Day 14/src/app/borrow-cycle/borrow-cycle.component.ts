import { Component } from '@angular/core';
import { ApiserviceService } from '../service';
import { HttpClient } from '@angular/common/http';
import { cycleList } from '../cycle-interface';

@Component({
  selector: 'app-borrow-cycle',
  templateUrl: './borrow-cycle.component.html',
  styleUrls: ['../cycle-list/cycle-list.component.css'],
})
export class BorrowCycleComponent {
  title = 'cycle-list';
  newdata: cycleList[] = [];


  constructor(
    private _apiserver: ApiserviceService,
    private http: HttpClient,
  ) {}

  ngOnInit() {
    this._apiserver.getData().subscribe(
      (res) => {
          this.newdata = res.filter(item => item.numAvailable > 0);
          console.log('Success: Response from API:', this.newdata);
      },
      (error) => {
        console.error('Error: Failed to fetch data from API:', error);
      }
    );
  }

  borrowCycle(cycleId: number) {
    const cycleBorrow = {
      id: cycleId,
      count: 1,
    };
    this._apiserver.borrowCycle(cycleBorrow)
    .subscribe(
        (response) => {
          console.log('Cycle borrow successfully:', cycleId);
          console.log('Response:', response);
          this.ngOnInit();
         },
         (error) => {
           console.error('Error in cycle borrow:', error);
         }
      );
    console.log(`Borrowing cycle with ID: ${cycleId}`);
  }

}
