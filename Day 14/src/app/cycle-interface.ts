export interface cycle {
  id: number;
  count: number;
}

export interface addNewCycle {
  brand: string;
  stock: number;
}

export interface cycleList {
  id: number;
  brand: string;
  stock: number;
  numBorrowed: number;
  numAvailable: number;
}
