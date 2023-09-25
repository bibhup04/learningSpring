import { Component, Input, SimpleChanges  } from '@angular/core';
import { BillItem } from '../product-interface';

@Component({
  selector: 'app-bill',
  templateUrl: './bill.component.html',
  styleUrls: ['./bill.component.css']
})
export class BillComponent {

  // @Input() bill: BillItem[] | null = null;
  // @Input() billAmount: number = 0;

  @Input() billData: { bill: BillItem[]; billAmount: number } = { bill: [], billAmount: 0 };


  // totalAmount: number = 0;

  
  // calculateTotalAmount() {
  //   console.log('Calculating total amount...');
  //   if (this.bill != null) {
  //     this.totalAmount = this.bill.reduce(
  //       (total, item) => {
  //         console.log(`Item: ${item.productName}, Price: ${item.price}, Quantity: ${item.quantity}`);
  //         return total + item.price * item.quantity;
  //       },
  //       0
  //     );
  //     console.log(`Total Amount: ${this.totalAmount}`);
  //   }
  // }
  

}
