import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'dateTime'
})
export class DateTimePipe implements PipeTransform {

  transform(createdAt: any): string {
    if (!(createdAt instanceof Date) && typeof createdAt === 'string') {
      createdAt = new Date(createdAt);
    }

    const now = new Date();
    const diffInHours = (now.getTime() - createdAt.getTime()) / (1000 * 60 * 60);

    if (diffInHours < 24) {
      const options: Intl.DateTimeFormatOptions = {
        hour: 'numeric',
        minute: 'numeric',
        second: 'numeric',
      };
      return createdAt.toLocaleString(undefined, options);
    } else {
      const options: Intl.DateTimeFormatOptions = {
        weekday: 'short',
        month: 'short',
        day: 'numeric',
        year: 'numeric'
      };
      return createdAt.toLocaleString(undefined, options);
    }
  }
}
