

export interface Product {
    id: string;
    name: string;
    price: number;
    quantity: number;
  }
  
  export interface Section {
    id: string;
    name: string;
    products: Product[];
    isDoorLocked: boolean;
  }
  

export interface BillItem {
    productName: string;
    quantity: number;
    price: number;
  }
  