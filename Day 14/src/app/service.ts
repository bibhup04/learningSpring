import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { cycle } from './cycle-interface';
import { addNewCycle , cycleList} from './cycle-interface';

// interface cycle {
//   id: number;
//   count: number;
// }

// interface addNewCycle {
//   brand: string;
//   stock: number;
// }

@Injectable({
  providedIn: 'root',
})
export class ApiserviceService {
  cycleData: any;
  constructor(private _http: HttpClient) {}

  private apiUrl = 'http://localhost:8080/api/cycle';

  getData() : Observable<cycleList[]> {
    return this._http.get<cycleList[]>(`${this.apiUrl}/list`);
  }

  borrowCycle(data: cycle): Observable<any> {
    return this._http.post(`${this.apiUrl}/${data.id}/borrow`, data, {
      responseType: 'text',
    });
  }

  returnCycle(data: cycle): Observable<any> {
    return this._http.post(`${this.apiUrl}/${data.id}/return`, data, {
      responseType: 'text',
    });
  }

  addCycle(data: addNewCycle){
    return this._http.post(`${this.apiUrl}/add`, data, {
        responseType: 'text',
      });
  }

  restockCycle(data : cycle){
    return this._http.post(`${this.apiUrl}/${data.id}/restock`, data, {
        responseType: 'text',
      });
  }
}
