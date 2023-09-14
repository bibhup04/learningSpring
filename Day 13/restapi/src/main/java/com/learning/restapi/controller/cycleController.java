package com.learning.restapi.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.learning.restapi.entity.Cycle;
import com.learning.restapi.entity.User;
import com.learning.restapi.entity.addNewCycle;

import com.learning.restapi.service.CycleService;
import com.learning.restapi.service.DomainUserService;
import com.learning.restapi.service.RegistrationForm;
import com.learning.restapi.service.UserService;


@RestController
@RequestMapping("/api/cycle")
@CrossOrigin
public class cycleController {
    

    @Autowired
    private CycleService cycleService;

    @Autowired
    private DomainUserService domainUserService;

    @Autowired
    private UserService userService;

    @GetMapping("/health")
    public String checkhealth() {
        return "healthy";
    }


     @GetMapping("/list")
    public ResponseEntity<List<Cycle>> listAvailableCycles() {

        var allCycles = cycleService.listAvailableCycles();
        return ResponseEntity.status(HttpStatus.OK).body(allCycles);
    }


    @PostMapping("/{id}/borrow")
    public ResponseEntity<String> borrowCyclePostMapping(@PathVariable long id, @RequestBody Map<String, Integer> requestData) {
        int count = requestData.getOrDefault("count", 1);
        cycleService.borrowCycle(id, count);
        return ResponseEntity.status(HttpStatus.OK).body("Cycle borrowed successfully");
    }

    @PostMapping("/{id}/return")
    public ResponseEntity<String> returnCycle(@PathVariable long id, @RequestBody Map<String, Integer> requestData) {
        int count = requestData.getOrDefault("count", 1); // Default value is 1 if "count" is not provided
        cycleService.returnCycle(id, count);
        return ResponseEntity.status(HttpStatus.OK).body("Cycle returned successfully");
    }

    @PostMapping("/add")
    public ResponseEntity<String> addCycle(@RequestBody addNewCycle addCycle){
        
        cycleService.addCycle(addCycle);

        return ResponseEntity.status(HttpStatus.OK).body("Cycle added successfully");
    }


    @PostMapping("/{id}/restock")
    public ResponseEntity<String> restockCycle(@PathVariable long id, @RequestBody Map<String, Integer> requestData) {
        int count = requestData.getOrDefault("count", 1);
        cycleService.restockBy(id, count);
        return ResponseEntity.status(HttpStatus.OK).body("Cycle restocked successfully");
    }

    @GetMapping("/")
    @ResponseBody
    public List<Cycle> listUsers() {
        return cycleService.listCycles();
    }

 

 

    @PostMapping("/post/register")
    public ResponseEntity<String> registerUser(@RequestBody RegistrationForm registrationForm) {
        try {
            registrationForm.getUsername();
            if (domainUserService.getByName(registrationForm.getUsername()) == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
            }
            if(!registrationForm.getPassword().equals(registrationForm.getRepeatPassword())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password doesnot match");
            }

            System.out.println(domainUserService.save(registrationForm.getUsername(), registrationForm.getPassword(), registrationForm.getRole()));
           
            return ResponseEntity.ok("User registered successfully");
        
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed: " + e.getMessage());
        }

    }

 
    
}
