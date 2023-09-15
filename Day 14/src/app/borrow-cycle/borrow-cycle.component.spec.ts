import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BorrowCycleComponent } from './borrow-cycle.component';

describe('BorrowCycleComponent', () => {
  let component: BorrowCycleComponent;
  let fixture: ComponentFixture<BorrowCycleComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BorrowCycleComponent]
    });
    fixture = TestBed.createComponent(BorrowCycleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
