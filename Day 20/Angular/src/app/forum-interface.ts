
export interface PostDetailResponse {
  post: Post;
  userName: string;
  commentList: Comment[];
  likeCount: number;
}

export interface User {
    id: number;
    name: string;
    password: string;
  }
  
  export interface Post {
    id: number;
    content: string;
    author: User;
    createdAt: Date;
    updatedAt: Date;
  }

  export interface Comment {
    commentId: number;
    commentText: string;
    user: User;
    post: Post;
    parent: Comment | null; // Allow for null or a Comment object
    createdAt: Date;
    updatedAt: Date;
  }
  