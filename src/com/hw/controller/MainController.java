package com.hw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
		
	@RequestMapping("/main.do")
	public String main() {
		return "main";
	}

	
}



