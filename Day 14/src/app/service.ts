import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

interface cycle {
  id: number;
  count: number;
}

interface addNewCycle {
  brand: string;
  stock: number;
}

@Injectable({
  providedIn: 'root',
})
export class ApiserviceService {
  cycleData: any;
  constructor(private _http: HttpClient) {}

  private apiUrl = 'http://localhost:8080/api/cycle';

  getData() {
    return this._http.get(`${this.apiUrl}/list`);
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
