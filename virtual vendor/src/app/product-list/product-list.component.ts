import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent {
  @Input() products: any[] | null = null; 
  @Output() purchaseProduct = new EventEmitter<any>();


  purchase(product: any) {
    this.purchaseProduct.emit(product);
  }

}
