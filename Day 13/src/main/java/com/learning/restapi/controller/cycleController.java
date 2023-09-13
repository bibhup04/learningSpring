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

import com.learning.restapi.entity.addNewCycle;

import com.learning.restapi.service.CycleService;
import com.learning.restapi.service.DomainUserService;
import com.learning.restapi.service.RegistrationForm;


@RestController
@RequestMapping("/api/cycle")
@CrossOrigin
public class cycleController {
    

    @Autowired
    private CycleService cycleService;

    @Autowired
    private DomainUserService domainUserService;

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

    //  @PostMapping("/register")
    // public String register(@ModelAttribute("registrationForm") RegistrationForm registrationForm, 
    // BindingResult bindingResult, 
    // RedirectAttributes attr) {
    //     System.out.println("id:" + registrationForm.getUsername());
    //     System.out.println("id:" + registrationForm.getPassword());
    //     System.out.println("id:" + registrationForm.getRepeatPassword());
    //     System.out.println("id:" + registrationForm.getRole());
    //     if (bindingResult.hasErrors()) {
    //     attr.addFlashAttribute("org.springframework.validation.BindingResult.registrationForm", bindingResult);
    //     attr.addFlashAttribute("registrationForm", registrationForm);
    //     return "redirect:/register";
    //     }
    //     if (!registrationForm.isValid()) {
    //     attr.addFlashAttribute("message", "Passwords must match");
    //     attr.addFlashAttribute("registrationForm", registrationForm);
    //     return "redirect:/register";
    //     }
    //     System.out.println(domainUserService.save(registrationForm.getUsername(), registrationForm.getPassword(), registrationForm.getRole()));
    //     attr.addFlashAttribute("result", "Registration success!");
    //     return "redirect:/login";
    // }
    
}
