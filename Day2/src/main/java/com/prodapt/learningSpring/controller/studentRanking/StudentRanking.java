package com.prodapt.learningSpring.controller.studentRanking;

import java.io.IOException; 
import java.util.ArrayList;
import java.util.Collections;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.prodapt.learningSpring.model.students.Students;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class StudentRanking {
	ArrayList<ArrayList<String>> twoDArrayList = new ArrayList<>();
	String name="";
	String score;
	ArrayList<Students> studentList = new ArrayList<>();
	
	public void sortStudentList() {
		Collections.sort(studentList, (student1, student2) -> student1.compareTo(student2));
	}
	
	public int updateID() {
		int maxId = 0;
        
        for (Students student : studentList) {
            int studentId = student.getId();
            if (studentId > maxId) {
                maxId = studentId;
            }
        }
        System.out.println(maxId+1);
        return maxId+1;
    }
	
	public void updateRank() {
		int i =0;
		int j =0;
		while(j<studentList.size()) {
			if(studentList.get(j).getScore()==studentList.get(i).getScore()) {
				studentList.get(j).setRank(i+1);
			}
			else {
				i=j;
				studentList.get(j).setRank(i+1);
			}
			j+=1;
		}
	}
	
	public void sortTwoDArrayListByScore() {
		Collections.sort(twoDArrayList, (list1, list2) -> {
		    double score1 = Double.parseDouble(list1.get(1));
		    double score2 = Double.parseDouble(list2.get(1));
		    return Double.compare(score2, score1); 
		});
    }
	
	@GetMapping("/studentRanking")
    public String showStudentForm(Model model) {
		
		updateRank();
		System.out.println(studentList);
		
		model.addAttribute("studentList", studentList);
		
		//model.addAttribute("twoDArrayList", twoDArrayList);
        return  "studentRanking";
    }

 

    @PostMapping("/studentRanking")
    public String processStudentForm(String p1name, String p1score ,HttpServletResponse resp) throws IOException {
    	name = p1name;
    	score = p1score;
    	
    	ArrayList<String> innerList1 = new ArrayList<>();
        innerList1.add(name);
        innerList1.add(score);
        twoDArrayList.add(innerList1);
        sortTwoDArrayListByScore();
        
    	Students student = new Students(p1name, Integer.parseInt(p1score));
    	student.setId(updateID());
    	studentList.add(student);
    	sortStudentList();
        return "redirect:/studentRanking";
    }
    
    @PostMapping("/deleteStudent")
    public String deleteStudent(@RequestParam("index") int index) {
//        if (index >= 0 && index < twoDArrayList.size()) {
//            twoDArrayList.remove(index);
//            studentList.remove(index);
//        }
        
        if (index >= 0 && index < studentList.size()) {
            twoDArrayList.remove(index);
            studentList.remove(index);
        }
        return "redirect:/studentRanking";
    }
    
    @PostMapping("/editStudent")
    public String editStudent(@RequestParam("index") int index, Model model) {
//		  System.out.println(index);
//        if (index >= 0 && index < twoDArrayList.size()) {
//            ArrayList<String> student = twoDArrayList.get(index);
//            model.addAttribute("student", student);
//            model.addAttribute("index", index);
//        }
        
        if (index >= 0 && index < studentList.size()) {
         
            model.addAttribute("student", studentList.get(index));
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
            studentList.get(index).updateData(editedName, Integer.parseInt(editedScore));
        }
        sortTwoDArrayListByScore();
        sortStudentList();
        return "redirect:/studentRanking";
    }


}
