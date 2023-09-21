import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {Post, User} from './forum-interface';

@Injectable({
    providedIn: 'root',
  })
  export class ApiserviceService {
    
    constructor(private _http: HttpClient) {}
  
    private apiUrl = 'http://localhost:8080/forum/post';
  
    getPost(postId : number) : Observable<Post> {
      return this._http.get<Post>(`${this.apiUrl}/show/${postId}`);
    }
  
  }
  