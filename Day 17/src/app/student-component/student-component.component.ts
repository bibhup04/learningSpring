import { Component } from '@angular/core';

@Component({
  selector: 'app-student-component',
  templateUrl: './student-component.component.html',
  styleUrls: ['./student-component.component.css']
})
export class StudentComponentComponent {
  students = [
    { Sid: 1, Sname: 'Bibhu', dob: '2001-01-04', course: 'ECE', fees: 10500 },
    { Sid: 2, Sname: 'Ankush', dob: '1998-03-20', course: 'CSE', fees: 18200 },
    { Sid: 3, Sname: 'Gaurav', dob: '1990-10-14', course: 'EEE', fees: 13300 },
    { Sid: 4, Sname: 'Abhishek', dob: '2003-11-15', course: 'EIE', fees: 15400 }
  ];

}
