package com.assessment.secondassessment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;



@Entity
@Data
public class Cycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int availableCycle; 

    private int borrowedCycle; 


    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getAvailableCycle() {
        return availableCycle;
    }

    public void setAvailableCycle(int availableCycle) {
        this.availableCycle = availableCycle;
    }


    public int getBorrowedCycle() {
        return borrowedCycle;
    }

    public void setBorrowedCycle(int borrowedCycle) {
        this.borrowedCycle = borrowedCycle;
    }

}
