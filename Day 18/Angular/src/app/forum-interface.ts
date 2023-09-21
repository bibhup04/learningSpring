

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
  