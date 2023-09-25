import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { ApiserviceService } from './service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'forum';
 
  postId: number = 0;

  constructor(private _apiserver: ApiserviceService, private http:HttpClient,private router: Router) {}

  navigateToPostDetail() {
   // this.router.navigate(['/home'], { queryParams: { param: this.postId } });

    this.router.navigate(['/home', this.postId]);

  }
}
