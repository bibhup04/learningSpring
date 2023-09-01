package com.learning.webpagewithspring.controller.binding;

//import lombok.Data;
//
//@Data
public class AddCycleForm {
	private String name;
    private String color;
    private boolean available;
    
    public String getName() {
        return name;
    }
    public String getColor() {
        return color;
    }
    public boolean getAvailable() {
        return available;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }

}
