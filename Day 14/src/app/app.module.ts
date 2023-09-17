import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { AddCycleComponent } from './add-cycle/add-cycle.component';
import { FormsModule } from '@angular/forms';
import { CycleListComponent } from './cycle-list/cycle-list.component';
import { BorrowCycleComponent } from './borrow-cycle/borrow-cycle.component';
import { ReturnCycleComponent } from './return-cycle/return-cycle.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';



@NgModule({
  declarations: [
    AppComponent,
    AddCycleComponent,
    CycleListComponent,
    BorrowCycleComponent,
    ReturnCycleComponent,
    NavBarComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
