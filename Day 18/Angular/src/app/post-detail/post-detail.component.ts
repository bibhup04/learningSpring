import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { ApiserviceService } from '../service';
import {Post, User} from '../forum-interface';

 

@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.css']
})
export class PostDetailComponent {
  postId:number = 0;
  post:Post | null = null;

  constructor(private _apiserver: ApiserviceService, private http:HttpClient,private route: ActivatedRoute) {}

  ngOnInit() {
    // this.route.queryParams.subscribe((params) => {
    //   this.postId= params['param'];
    //   console.log(this.postId);
    //   if(this.postId != 0){
    //     this.showPost();
    //   }
    // });

    // const postIdParam = this.route.snapshot.paramMap.get('postId');
    // if (postIdParam !== null) {
    //   this.postId = +postIdParam;
    //   this.showPost();
    // }

    this.route.params.subscribe((params) => {
      const postIdParam = params['postId'];
      if (postIdParam !== null) {
        this.postId = +postIdParam;
        this.showPost();
      }
    });

    //this.postId = this.route.snapshot.paramMap.get('postId');
  }
  showPost(){
    this._apiserver.getPost(this.postId).subscribe(
        response => {
          this.post = response;
          console.log('Id send successfully', response);
        },
        error => {
          console.error('Error in displaying post:', error);
          alert("No post for the given id");
        }
      );
      this.postId=0;
  }

}
