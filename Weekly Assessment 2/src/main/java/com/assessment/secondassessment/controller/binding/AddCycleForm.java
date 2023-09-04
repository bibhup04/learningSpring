package com.assessment.secondassessment.controller.binding;


//import lombok.Data;
//
//@Data
public class AddCycleForm {
    private String name;
    private int availableCycle; 
    private int borrowedCycle; 

    public String getName() {
        return name;
    }

    public int getAvailableCycle() {
        return availableCycle;
    }

    public int getBorrowedCycle() {
        return borrowedCycle;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvailableCycle(int availableCycle) {
        this.availableCycle = availableCycle;
    }

    public void setBorrowedCycle(int borrowedCycle) {
        this.borrowedCycle = borrowedCycle;
    }
}
