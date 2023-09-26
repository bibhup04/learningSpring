import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';



import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PostDetailComponent } from './post-detail/post-detail.component';
import { BaseLayoutComponent } from './base-layout/base-layout.component';
import { DateTimePipe } from './date-time.pipe';
import { LikeComponent } from './like/like.component';
import { CommentComponent } from './comment/comment.component';
import { PostComponent } from './post/post.component';
import { DirectiveNameDirective } from './directive-name.directive';
import { UserTagDirective } from './user-tag.directive';
import { MentionTransformPipe } from './mention-transform.pipe';

@NgModule({
  declarations: [
    AppComponent,
    PostDetailComponent,
    BaseLayoutComponent,
    DateTimePipe,
    LikeComponent,
    CommentComponent,
    PostComponent,
    DirectiveNameDirective,
    UserTagDirective,
    MentionTransformPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
