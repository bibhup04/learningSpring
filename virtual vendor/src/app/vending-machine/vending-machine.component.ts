import { Component } from '@angular/core';
import { Product, Section, BillItem } from '../product-interface'; 

@Component({
  selector: 'app-vending-machine',
  templateUrl: './vending-machine.component.html',
  styleUrls: ['./vending-machine.component.css']
})
export class VendingMachineComponent {

  isDoorOpen: boolean = false;
  isDoorLocked:boolean = true;
  selectedProductId: string = '';
  selectedSection: any;
  billAmount: number = 0;
  bill: BillItem[] = [];

  sections: Section[] = [
    {
      id: 'softDrinks',
      name: 'Soft Drinks',
      products: [
        { id: '001', name: 'Coca-Cola', price: 1.0, quantity: 5 },
        { id: '002', name: 'Pepsi', price: 1.0, quantity: 4 }
      ],
      isDoorLocked: true
    },
    {
      id: 'lightSnacks',
      name: 'Light Snacks',
      products: [
        { id: '101', name: 'Chips', price: 0.75, quantity: 10 },
        { id: '102', name: 'Granola Bar', price: 1.25, quantity: 8 }
      ],
      isDoorLocked: true
    },
    {
      id: 'heavySnacks',
      name: 'Heavy Snacks',
      products: [
        { id: '201', name: 'Chocolate Bar', price: 1.5, quantity: 6 },
        { id: '202', name: 'Sausage Rolls', price: 2.0, quantity: 7 }
      ],
      isDoorLocked: true
    }
  ];

  unlockDoor(){
    this.billAmount = 0;
    this.bill = []; 
    const sectionToUnlock = this.sections.find(section =>
      section.products.some(product => product.id === this.selectedProductId)
    );
  
    if (sectionToUnlock) {
      sectionToUnlock.isDoorLocked = false;
      this.isDoorLocked = false;
      console.log(`Door unlocked for product: ${this.selectedProductId} in section: ${sectionToUnlock.name}`);
    } else {
      console.log(`Product not found: ${this.selectedProductId}`);
    }
    
  }
  
  openSection(sectionId: string) {
    console.log("sectionId", sectionId);
    this.isDoorOpen = true;
    this.selectedProductId = sectionId;
    this.selectedSection = this.sections.find(section => section.id === sectionId);
  }

  closeDoor() {
    this.selectedSection.isDoorLocked = true;
    this.isDoorLocked = true;
    this.isDoorOpen = false;
    this.selectedProductId = '';
    this.selectedSection = null;
  }


  purchaseProduct(product: any) {

    if (product.quantity > 0) {
      product.quantity--;
      this.billAmount += product.price;
      console.log("bill amount in vendor component",this.billAmount);
      const totalPrice = product.price * 1;
      this.addBillItem(product.name, 1, totalPrice);
    }
  }

  // addBillItem(productName: string, quantity: number, price: number) {
  //   this.bill.push({ productName, quantity, price });
  // }

  addBillItem(productName: string, quantity: number, price: number) {

    const existingItem = this.bill.find((item) => item.productName === productName);
  
    if (existingItem) {
      existingItem.quantity += quantity;
      existingItem.price += price;
    } else {
      this.bill.push({ productName, quantity, price });
    }
  }
  

}
