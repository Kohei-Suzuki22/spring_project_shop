package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	@GetMapping(path="/")
	public String index() {
		return "index";
	}
	
	@GetMapping(path="/before")
	public String before() {
		return "before";
	}
	
	@GetMapping(path="/after")
	public String after() {
		return "after";
	}
	
	@GetMapping(path="/forward_redirect")
	public String forward_redirect() {
		return "forward_redirect";
	}
	
	@GetMapping(path="/index_forward")
	public String index_forward() {
		return "index";
	}
	
	@GetMapping(path="/index_redirect")
	public String index_redirect() {
		return "redirect:/";
	}

}
