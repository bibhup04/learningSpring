package com.learning.webpagewithspring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.learning.webpagewithspring.controller.binding.AddCycleForm;
import com.learning.webpagewithspring.entity.Cycle;
import com.learning.webpagewithspring.repository.cycleRepository;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletException;

@Controller
public class CycleController {
	
	@Autowired
	private cycleRepository cyclerepository;
	
	private List<Cycle> borrowCycleList;
	private List<Cycle> returnCycleList;
	private List<Cycle> cycleList;
	//private Optional<Cycle> cycle;
	
	@PostConstruct
	public void init() {
		borrowCycleList = new ArrayList<>();
		returnCycleList = new ArrayList<>(); 
		cycleList = new ArrayList<>();
		cyclerepository.findAll().forEach(cycle -> cycleList.add(cycle));
		cyclerepository.findByAvailable(true).forEach(cycle -> borrowCycleList.add(cycle));
		cyclerepository.findByAvailable(false).forEach(cycle -> returnCycleList.add(cycle));
	}
	 
	@GetMapping("/cycleRent")
    public String showRentForm(Model model) {
		init();
		System.out.println(borrowCycleList);
		model.addAttribute("cycleForm", new AddCycleForm());
		model.addAttribute("borrowCycleList", borrowCycleList);
		model.addAttribute("cycleList", cycleList);
		model.addAttribute("returnCycleList", returnCycleList);
		

        return  "cycleRent";
    }
	
	 @PostMapping("/cycleRent/rent")
	    public String processSelectedCycle(@RequestParam("selectedCycleId") int selectedCycleId) {
	        System.out.println(selectedCycleId);
	        Optional<Cycle> cycle = Optional.ofNullable(cyclerepository.findById(selectedCycleId));
	        Cycle c = cycle.get();
	        c.setAvailable(false);
	        cyclerepository.save(c);
	        return "redirect:/cycleRent";// cycleRepository.findById(selectedCycleId);
	    }
	
	 @PostMapping("/cycleRent/return")
	    public String processReturnCycle(@RequestParam("returnCycleId") int returnCycleId) {
	        System.out.println(returnCycleId);
	        Optional<Cycle> cycle = Optional.ofNullable(cyclerepository.findById(returnCycleId));
	        Cycle c = cycle.get();
	        c.setAvailable(true);
	        cyclerepository.save(c);
	        return "redirect:/cycleRent";// cycleRepository.findById(selectedCycleId);
	    }
	 
	 @PostMapping("/cycleRent/add")
	  public String addNewPost(@ModelAttribute("cycleForm") AddCycleForm cycleForm, BindingResult bindingResult, RedirectAttributes attr) throws ServletException {
	    if (bindingResult.hasErrors()) {
	      System.out.println(bindingResult.getFieldErrors());
	      attr.addFlashAttribute("org.springframework.validation.BindingResult.post", bindingResult);
	      attr.addFlashAttribute("post", cycleForm);
	      return "redirect:/cycleRent";
	    }
	    System.out.println("inside post add");
	    
	    Cycle cycle = new Cycle();

	    cycle.setName(cycleForm.getName());
	    String c = cycleForm.getColor();
	    cycle.setColor(c);
	    cycle.setAvailable(cycleForm.getAvailable());

	    cyclerepository.save(cycle);

	    return "redirect:/cycleRent";
	  }

}
