import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {CommentData, User,PostDetailResponse} from './forum-interface';


@Injectable({
    providedIn: 'root',
  })
  export class ApiserviceService {
    
    constructor(private _http: HttpClient) {}
  
    private apiUrl = 'http://localhost:8080/forum/post';
  
    getPost(postId : number) : Observable<PostDetailResponse> {
      return this._http.get<PostDetailResponse>(`${this.apiUrl}/show/${postId}`);
    }

    getUsers(): Observable<User[]> {
      return this._http.get<User[]>(`${this.apiUrl}/user`);
    }

    // sendComment(commentText: string, postId: number): Observable<any> {
    //   //const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    //   const body = { commentText: commentText, postId: postId }; 
  
    //   return this._http.post(`${this.apiUrl}/addComment`, body, {responseType : 'text'});
    // }

    sendComment(commentData: CommentData): Observable<any> {
      //const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
      // const body = { commentText: commentText, postId: postId }; 
  
      return this._http.post(`${this.apiUrl}/addComment`, commentData, {responseType : 'text'});
    }
  
  }
  