import { Component } from '@angular/core';
import { ApiserviceService } from '../service';
import { cycleList } from '../cycle-interface';

@Component({
  selector: 'app-cycle-list',
  templateUrl: './cycle-list.component.html',
  styleUrls: ['./cycle-list.component.css']
})
export class CycleListComponent {
  title = 'cycle-list';
  newdata: cycleList[] = [];


  constructor(private _apiserver:ApiserviceService){}

  ngOnInit(){
    this._apiserver.getData().subscribe(
      (res) => {
        this.newdata = res;
        console.log('Success: Response from API:', this.newdata);
      },
      (error) => {
        console.error('Error: Failed to fetch data from API:', error);
      }
    );

  }

}
