package it.shop.shoes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class ControllerApiRest {
	
	
	
	@RequestMapping("/")
	public String welcomepage2() {
		return "funziona /";
	}
	
	@GetMapping("/a")
	public String welcomepage2sssss() {
		return "funziona A";
	}
		
	


}
