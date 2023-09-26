import { Pipe, PipeTransform } from '@angular/core';
import { User } from './forum-interface';


@Pipe({
  name: 'mentionTransform'
})
// export class MentionTransformPipe implements PipeTransform {

//   transform(commentText: string): string {

//     const mentionRegex = /@([a-zA-Z0-9_]+)/g;
//     console.log("mentionRegex-", mentionRegex)
//     // '<a href="/profile/$1">@$1</a>'

//     return commentText.replace(mentionRegex, '<a href="http://www.google.com">@$1</a>');
//   }

// }
export class MentionTransformPipe implements PipeTransform {
  transform(commentText: string, users: User[]): string {
    const mentionRegex = /@([a-zA-Z0-9_]+)/g;

    // Replace mentions with links where href is "/profile/username"
    const transformedText = commentText.replace(mentionRegex, (match, username) => {
      const user = users.find((u) => u.name === username);
      if (user) {
        return `<a href="/profile/${user.id}">@${username}</a>`;
      }
      return match; // Return the mention as-is if no matching user is found
    });

    return transformedText;
  }
}
