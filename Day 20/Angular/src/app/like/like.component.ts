
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-like',
  templateUrl: './like.component.html',
  styleUrls: ['./like.component.css']
})
export class LikeComponent {
  @Input() likeCount: number | null = null;

  isLiked = false; // Initialize as not liked

  toggleLike() {
    this.isLiked = !this.isLiked; // Toggle the like state
  }

}
