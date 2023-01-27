package it.shop.shoes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/shoes")
public class ControllerTwo {
	
	
	
	@RequestMapping("/b")
	public String welcomepage2() {
		return "funziona B";
	}
	
	@GetMapping("/c")
	public String welcomepage2sssss() {
		return "funziona C";
	}

}
