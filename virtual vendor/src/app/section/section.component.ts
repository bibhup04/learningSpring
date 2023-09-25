import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Section } from '../product-interface';

@Component({
  selector: 'app-section',
  templateUrl: './section.component.html',
  styleUrls: ['./section.component.css']
})
export class SectionComponent {
  @Input() section: any;
  @Input() isDoorOpen?: boolean; 
  @Input() isDoorLocked?: boolean;
  @Output() openDoor = new EventEmitter<string>(); 


  openSection(sectionId: string) {

    if (this.isDoorLocked) {
      console.log("The door is locked. Please unlock it to open.");
      return;
    }
    if (!this.isDoorOpen) {
      this.openDoor.emit(sectionId);
    }
  }
}
