package com.assessment.secondassessment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class projectController {
	
	@GetMapping("/project")
	public String getProject(Model model) {
		return "home";	
	}

}
