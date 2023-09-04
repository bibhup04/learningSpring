package com.assessment.secondassessment.controller;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.assessment.secondassessment.controller.binding.AddCycleForm;
import com.assessment.secondassessment.entity.Cycle;
import com.assessment.secondassessment.repository.cycleRepository;


import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletException;

@Controller
public class CycleController {
	
	@Autowired
	private cycleRepository cyclerepository;
	
	private List<Cycle> borrowedCycleList;
	private List<Cycle> availableCycleList;
	private List<Cycle> cycleList;
	//private Optional<Cycle> cycle;
	
	
	public void init(Model model) {  
		borrowedCycleList = new ArrayList<>();
		availableCycleList = new ArrayList<>(); 
		cycleList = new ArrayList<>();
		cyclerepository.findAll().forEach(cycle -> cycleList.add(cycle));
		
		for (Cycle cycle : cycleList) {
	        if (cycle.getAvailableCycle() > 0) {
	        	System.out.println("Inside available");
	            availableCycleList.add(cycle);
	        }
	        if (cycle.getBorrowedCycle() > 0) {
	        	System.out.println("Inside borrow");
	        	borrowedCycleList.add(cycle);
	        }
	    }
		model.addAttribute("cycleForm", new AddCycleForm());
		model.addAttribute("availableCycleList", availableCycleList);
		model.addAttribute("cycleList", cycleList);
		model.addAttribute("borrowedCycleList", borrowedCycleList);

	}
	 
	@GetMapping("/cycleRent")
    public String showRentForm(Model model) {
		init(model);
		System.out.println("cycleList:");
	    for (Cycle cycle : cycleList) {
	        System.out.println("ID: " + cycle.getId() + ", Name: " + cycle.getName() + ", Available: " + cycle.getAvailableCycle() + ", Borrowed: " + cycle.getBorrowedCycle());
	    }

	    System.out.println("availableCycleList:");
	    for (Cycle cycle : availableCycleList) {
	        System.out.println("ID: " + cycle.getId() + ", Name: " + cycle.getName() + ", Available: " + cycle.getAvailableCycle() + ", Borrowed: " + cycle.getBorrowedCycle());
	    }
	    for (Cycle cycle : borrowedCycleList) {
	        System.out.println("ID: " + cycle.getId() + ", Name: " + cycle.getName() + ", Available: " + cycle.getAvailableCycle() + ", Borrowed: " + cycle.getBorrowedCycle());
	    }
		 
        return  "cycleRent";
    }
	
	
	 @GetMapping("/borrow/{cycleId}")
	    public String borrowCycle(@PathVariable("cycleId") int cycleId, Model model) {

	        System.out.println("Borrowing cycle with ID: " + cycleId);
	        
	        Cycle cycle = cyclerepository.findById(cycleId).orElse(null);
	        if (cycle != null && cycle.getAvailableCycle() > 0) {
	        	cycle.setAvailableCycle(cycle.getAvailableCycle() - 1);
	            cycle.setBorrowedCycle(cycle.getBorrowedCycle() + 1);

	            cyclerepository.save(cycle);
	        }
	        
	        init(model);
	        return "cycleRent"; 
	    }
	 
	 
	 @GetMapping("/return/{cycleId}")
	 public String restockCycle(@PathVariable("cycleId") int cycleId, @RequestParam("returnCycleId") int returnQuantity, Model model) {

	     System.out.println("Returning cycle with ID: " + cycleId);
	     System.out.println("Returned Cycle ID from textbox: " + returnQuantity);
	     
	     Cycle cycle = cyclerepository.findById(cycleId).orElse(null);
	        if (cycle != null && cycle.getBorrowedCycle() > returnQuantity) {
	        	cycle.setAvailableCycle(cycle.getAvailableCycle() + returnQuantity);
	            cycle.setBorrowedCycle(cycle.getBorrowedCycle() - returnQuantity);

	            cyclerepository.save(cycle);
	        }

	        init(model);
	        return "cycleRent"; 
	 }
	 
	 @PostMapping("/cycleRent/add")
	 public String createCycle(@ModelAttribute("cycleForm") AddCycleForm cycleForm) {

	     System.out.println("Name: " + cycleForm.getName());
	     System.out.println("Available Quantity: " + cycleForm.getAvailableCycle());
	     System.out.println("Borrowed Quantity: " + cycleForm.getBorrowedCycle());
	     
	     Cycle cycle = new Cycle();
	     cycle.setName(cycleForm.getName());
	     cycle.setAvailableCycle(cycleForm.getAvailableCycle());
	     cycle.setBorrowedCycle(cycleForm.getBorrowedCycle());

	     cyclerepository.save(cycle);

	     
	     return "redirect:/cycleRent";
	 }


	

	 


}
