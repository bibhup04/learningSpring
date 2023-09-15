import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CycleListComponent } from './cycle-list/cycle-list.component';
import { AddCycleComponent } from './add-cycle/add-cycle.component';
import { BorrowCycleComponent } from './borrow-cycle/borrow-cycle.component';
import { ReturnCycleComponent } from './return-cycle/return-cycle.component';

const routes: Routes = [
  { path: 'home', component: CycleListComponent },
  { path: 'addcycle', component: AddCycleComponent },
  { path: 'borrowcycle', component: BorrowCycleComponent},
  { path: 'returncycle', component: ReturnCycleComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
