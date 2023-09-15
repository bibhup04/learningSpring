import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReturnCycleComponent } from './return-cycle.component';

describe('ReturnCycleComponent', () => {
  let component: ReturnCycleComponent;
  let fixture: ComponentFixture<ReturnCycleComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ReturnCycleComponent]
    });
    fixture = TestBed.createComponent(ReturnCycleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
