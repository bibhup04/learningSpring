import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { ApiserviceService } from '../service';

@Component({
  selector: 'app-add-cycle',
  templateUrl: './add-cycle.component.html',
  styleUrls: ['./add-cycle.component.css']
})

export class AddCycleComponent {
  cycleBrand: string = '';
  stock: number = 0;
  id: number=0;
  count: number=0;

  constructor(private _apiserver: ApiserviceService, private http:HttpClient) {}

  submitForm() {
    const cycleData = {
      brand: this.cycleBrand,
      stock: this.stock
    };

    this._apiserver.addCycle(cycleData).subscribe(
        response => {
          console.log('Cycle added successfully:', response);
          this.cycleBrand = "";
          this.stock = 0;
        },
        error => {
          console.error('Error adding cycle:', error);
        }
      );
  }

  restockForm(){
    const restockData = {
      id : this.id,
      count : this.count
    };
    this._apiserver.restockCycle(restockData)
    .subscribe(
        response => {
          console.log('Cycle restock successfully:', response);
          console.log('amount', restockData)
          this.id = 0;
          this.count = 0;
        },
        error => {
          console.error('Error in cycle restock:', error);
        }
      );
  }
}
