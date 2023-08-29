package com.prodapt.learningSpring.controller.studentRanking;

import java.io.IOException; 
import java.util.ArrayList;
import java.util.Collections;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class StudentRanking {
	ArrayList<ArrayList<String>> twoDArrayList = new ArrayList<>();
	String name="";
	String score="";
	
	public void sortTwoDArrayListByScore() {
		Collections.sort(twoDArrayList, (list1, list2) -> {
		    double score1 = Double.parseDouble(list1.get(1));
		    double score2 = Double.parseDouble(list2.get(1));
		    return Double.compare(score1, score2); 
		});
    }
	
	@GetMapping("/studentRanking")
    public String showStudentForm(Model model) {
		System.out.println("inside get");
		System.out.println(name);
		if (!name.equals("")) {
            System.out.println("inside if");
            ArrayList<String> innerList1 = new ArrayList<>();
            innerList1.add(name);
            innerList1.add(score);
            twoDArrayList.add(innerList1);
            name = "";
            score = "";
        }
		sortTwoDArrayListByScore();
		System.out.println(twoDArrayList);
		model.addAttribute("twoDArrayList", twoDArrayList);
        return  "studentRanking";
    }

 

    @PostMapping("/studentRanking")
    public String processStudentForm(String p1name, String p1score ,HttpServletResponse resp) throws IOException {
    	name = p1name;
    	score = p1score;
    	System.out.println("inside post");
    	System.out.println(p1name);
    	System.out.println(p1score);
    	
        return "redirect:/studentRanking";
    }
    
    @PostMapping("/deleteStudent")
    public String deleteStudent(@RequestParam("index") int index) {
        if (index >= 0 && index < twoDArrayList.size()) {
            twoDArrayList.remove(index);
        }
        return "redirect:/studentRanking";
    }
    
    @PostMapping("/editStudent")
    public String editStudent(@RequestParam("index") int index, Model model) {
    	System.out.println(index);
        if (index >= 0 && index < twoDArrayList.size()) {
            ArrayList<String> student = twoDArrayList.get(index);
            model.addAttribute("student", student);
            model.addAttribute("index", index);
        }
        
        return "editStudent"; 
    }
    
    @PostMapping("/saveEdit")
    public String saveEdit(@RequestParam("index") int index, @RequestParam("editedName") String editedName, @RequestParam("editedScore") String editedScore) {
        if (index >= 0 && index < twoDArrayList.size()) {
            ArrayList<String> student = twoDArrayList.get(index);
            student.set(0, editedName);
            student.set(1, editedScore);
        }
        return "redirect:/studentRanking";
    }


}
