import { Component, Input, EventEmitter, Output } from '@angular/core';
import { Comment, User} from '../forum-interface';
import { ApiserviceService } from '../service';
import { MentionTransformPipe } from '../mention-transform.pipe';
import { CommentData } from '../forum-interface';



@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent {
  newCommentText:String ="";
  inputText: string = '';
  showSuggestions: boolean = false;
  matchedUsers: User[] = [];
  users: User[] = [];
  taggedUsers: User[] = [];

  constructor(private _apiserver: ApiserviceService){}

  @Input() comments: Comment[] | null = null;
  //@Output() commentAdded = new EventEmitter<string>();
  @Output() commentAdded = new EventEmitter<CommentData>();



  // addComment() {
  //   this.commentAdded.emit(this.inputText);
  //   this.inputText='';
  //   this.taggedUsers = [];
  // }

  addComment() {
    const commentData: CommentData = {
      commentText: this.inputText,
      taggedUsers: this.taggedUsers,
      postId: 0
    };
  
    this.commentAdded.emit(commentData);
    this.inputText = '';
    this.taggedUsers = [];
  }
  

  ngOnInit() {
    this.loadUsers();
  }

  loadUsers() {
    this._apiserver.getUsers().subscribe(
      (users) => {
        this.users = users;
      },
      (error) => {
        console.error('Error fetching users', error);
      }
    );
  }

  onInputChange() {
    const input = this.inputText.toLowerCase();

    if (input.includes('@')) {
      const query = input.substring(input.lastIndexOf('@') + 1);
      this.matchedUsers = this.users.filter((user) =>
        user.name.toLowerCase().includes(query)
      );
      this.showSuggestions = this.matchedUsers.length > 0;
    } else {
      this.showSuggestions = false;
    }
  }

  // mentionUser(user: User) {
  //   const mentionedText = `@${user.name} `;
  //   this.inputText = this.inputText.replace('@', mentionedText);
  //   this.showSuggestions = false;
  // }

  mentionUser(user: User) {

    const inputParts = this.inputText.split('@');
    const lastPart = inputParts.pop();
    this.inputText = inputParts.join('@') + `@${user.name} `;
    this.taggedUsers.push(user);
    this.showSuggestions = false;
  }
  
}
