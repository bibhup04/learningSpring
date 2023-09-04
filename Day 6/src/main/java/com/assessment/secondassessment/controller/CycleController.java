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
import com.assessment.secondassessment.controller.binding.LoginUser;
import com.assessment.secondassessment.encription.encriptPassword;
import com.assessment.secondassessment.entity.Cycle;
import com.assessment.secondassessment.repository.UserRepository;
import com.assessment.secondassessment.repository.cycleRepository;
import com.assessment.secondassessment.exception.ResourceNotFoundException;
import com.assessment.secondassessment.entity.User;




import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CycleController {
	
	@Autowired
	private cycleRepository cyclerepository;


	@Autowired
	private UserRepository userRepository;

	encriptPassword encriptpassword = new encriptPassword();
	
	private List<Cycle> borrowedCycleList;
	private List<Cycle> availableCycleList;
	private List<Cycle> cycleList;
	private List<Cycle> restockCycleList;
	//private Optional<Cycle> cycle;
	//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
	
	
	public void init(Model model) {  
		borrowedCycleList = new ArrayList<>();
		availableCycleList = new ArrayList<>();
		restockCycleList = new ArrayList<>();
		cycleList = new ArrayList<>();
		cyclerepository.findAll().forEach(cycle -> cycleList.add(cycle));
		
		for (Cycle cycle : cycleList) {
	        if (cycle.getAvailableCycle() > 0) {
	            availableCycleList.add(cycle);
	        }
	        if (cycle.getBorrowedCycle() > 0) {
	        	borrowedCycleList.add(cycle);
	        }
	        if (cycle.getRestockCycle() > 0) {
	        	restockCycleList.add(cycle);
	        }
	    }
		model.addAttribute("cycleForm", new AddCycleForm());
		model.addAttribute("availableCycleList", availableCycleList);
		model.addAttribute("cycleList", cycleList);
		model.addAttribute("borrowedCycleList", borrowedCycleList);
		model.addAttribute("restockCycleList", restockCycleList);
		model.addAttribute("loginUser", new LoginUser());

	}
	 
	@GetMapping("/cycleRent")
    public String showRentForm(Model model) {
		init(model);
		// System.out.println("cycleList:");
	    // for (Cycle cycle : cycleList) {
	    //     System.out.println("ID: " + cycle.getId() + ", Name: " + cycle.getName() + ", Available: " + cycle.getAvailableCycle() + ", Borrowed: " + cycle.getBorrowedCycle());
	    // }

	    // System.out.println("availableCycleList:");
	    // for (Cycle cycle : availableCycleList) {
	    //     System.out.println("ID: " + cycle.getId() + ", Name: " + cycle.getName() + ", Available: " + cycle.getAvailableCycle() + ", Borrowed: " + cycle.getBorrowedCycle());
	    // }
	    // for (Cycle cycle : borrowedCycleList) {
	    //     System.out.println("ID: " + cycle.getId() + ", Name: " + cycle.getName() + ", Available: " + cycle.getAvailableCycle() + ", Borrowed: " + cycle.getBorrowedCycle());
	    // }
	    // for (Cycle cycle : restockCycleList) {
	    //     System.out.println("ID: " + cycle.getId() + ", Name: " + cycle.getName() + ", Available: " + cycle.getAvailableCycle() + ", Borrowed: " + cycle.getBorrowedCycle() + ", Restock: " + cycle.getRestockCycle());
	    // }
		 
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
	 
	 @GetMapping("/Garage/{cycleId}")
	 public String garageMechanic(@PathVariable("cycleId") int cycleId, Model model) {

	     System.out.println("Cycle ID in Garage: " + cycleId);
	     
	     Cycle cycle = cyclerepository.findById(cycleId).orElse(null);
	        if (cycle != null && cycle.getAvailableCycle() > 0) {
	        	cycle.setAvailableCycle(cycle.getAvailableCycle() - 1);
	            cycle.setRestockCycle(cycle.getRestockCycle() + 1);

	            cyclerepository.save(cycle);
	        } 
	     
	     init(model);
	     return "cycleRent"; 
	 }

	 
	 
	 @GetMapping("/return/{cycleId}")
	 public String returnkCycle(@PathVariable("cycleId") int cycleId, Model model) {

	     System.out.println("Returning cycle with ID: " + cycleId);
	     System.out.println("Returned Cycle ID from textbox: " + 1);
	     
	     Cycle cycle = cyclerepository.findById(cycleId).orElse(null);
	        if (cycle != null && cycle.getBorrowedCycle() > 0) {
	        	cycle.setAvailableCycle(cycle.getAvailableCycle() + 1);
	            cycle.setBorrowedCycle(cycle.getBorrowedCycle() - 1);

	            cyclerepository.save(cycle);
	        }

	        init(model);
	        return "cycleRent"; 
	 }

	 
	 
	 @GetMapping("/restock/{cycleId}")
	 public String restockCycle(@PathVariable("cycleId") int cycleId, @RequestParam("restockCycle") int restockCycle, Model model) {

	     System.out.println("Restocking cycle with ID: " + cycleId);
	     System.out.println("Restock Cycle ID from textbox: " + restockCycle);
	     
	     Cycle cycle = cyclerepository.findById(cycleId).orElse(null);
	        if (cycle != null && cycle.getRestockCycle() >= restockCycle) {
	        	cycle.setAvailableCycle(cycle.getAvailableCycle() + restockCycle);
	            cycle.setRestockCycle(cycle.getRestockCycle() - restockCycle);

	            cyclerepository.save(cycle);
	        }

	     init(model);
	     return "cycleRestock"; 
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


	 @GetMapping("/restockCycle")
	 public String restockValidation(Model model){
		init(model); 
		return "cycleLogin"; 
	 }
	 
	 @PostMapping("/restock/login")
	    public String loginUser(@ModelAttribute LoginUser loginUser, HttpServletRequest request) {

	        System.out.println("User ID: " + loginUser.getUserId());
	        System.out.println("Password: " + loginUser.getPassword());
	        String hashedPassword = encriptpassword.encryptPassword(loginUser.getPassword());
	        System.out.println(hashedPassword);

			User user = userRepository.findByUserId(loginUser.getUserId());
			if (user != null && encriptpassword.isMatch(loginUser.getPassword(), user.getPassword())) {
				System.out.println("its matching");
				HttpSession session = request.getSession();
				session.setAttribute("userId", loginUser.getUserId());
				return "redirect:/restockCycle/" + loginUser.getUserId();
			} 
	        return "redirect:/restockCycle";
	    }

	@GetMapping("/restockCycle/{userId}")
    public String redirectToRestockCycle(@PathVariable String userId, Model model, HttpSession session) throws ResourceNotFoundException{

		String logginedId = (String) session.getAttribute("userId");
		User u = userRepository.findByUserId(userId);
		if (u == null && !logginedId.equals(userId)) {
			throw new ResourceNotFoundException("No user with the requested ID");
		}
		
	
		init(model);
		
		return "cycleRestock";
        
    }

	 @PostMapping("/registerUser")
	 public String registerUser( @RequestParam("username") String id,
		 @RequestParam("registerPassword") String registerPassword) {
		System.out.println("user id -"+ id);
		System.out.println("password -"+ registerPassword);
		String hashedPassword = encriptpassword.encryptPassword(registerPassword);

		User user = new User();
		user.setUserId(id);
		user.setPassword(hashedPassword);

		userRepository.save(user);


		System.out.println("Hashed Password: " + hashedPassword);
		 return "redirect:/restockCycle"; 
	 }

	 

	


}
