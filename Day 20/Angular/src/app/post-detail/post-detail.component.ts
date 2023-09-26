import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { ApiserviceService } from '../service';
import {Post, CommentData, PostDetailResponse} from '../forum-interface';

 

@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.css']
})
export class PostDetailComponent {
  postId:number = 0;
  post:Post | null = null;
  postDetailResponse: PostDetailResponse | null = null;
  displayComment: boolean = false;

  constructor(private _apiserver: ApiserviceService, private http:HttpClient,private route: ActivatedRoute) {}

  ngOnInit() {
    
    this.route.params.subscribe((params) => {
      const postIdParam = params['postId'];
      if (postIdParam !== null) {
        this.postId = +postIdParam;
        this.showPost();
      }
    });


  }

  showPost(){
    this._apiserver.getPost(this.postId).subscribe(
        response => {
          this.postDetailResponse = response;
          console.log('Id send successfully', response);
        },
        error => {
          this.postDetailResponse = null;
          console.error('Error in displaying post:', error);
          alert("No post for the given id");
        }
      );
      // this.postId=0;
  }

  showCommnet(){
    this.displayComment = !this.displayComment;
  }

  addComment(commentData: CommentData) {
    commentData.postId= this.postId;
    console.log('Received Comment Data:', commentData.postId);
    this._apiserver.sendComment(commentData).subscribe(
      response => {
              console.log('Comment sent successfully with tagged users:', response);
              this.ngOnInit();
            },
            error => {
              console.error('Error sending comment:', error);
       
            }
          );
        }

  // addComment(commentText: string, taggedUsers: []){
  //   console.log("comment send parent component", commentText);
  //   console.log("Post id", this.postId);

  //   this._apiserver.sendComment(commentText, this.postId).subscribe(
  //     response => {
  //       console.log('Comment sent successfully:', response);
  //       this.ngOnInit();
  //     },
  //     error => {
  //       console.error('Error sending comment:', error);
 
  //     }
  //   );
  // }

}
