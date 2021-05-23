package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.LoginForm;

@Controller
public class SessionController {
	
	@GetMapping("session/login")
	public String login() {
		return "/session/login";
	}
	
	@PostMapping("/session/login")
	public String doLoginOnSession(LoginForm form, HttpSession session) {
		
		if (form.getUserId() == 123) {
			session.setAttribute("userId", form.getUserId());
			System.out.println(session.getAttribute("userId"));
			return "redirect:/";
		}else {
			return "redirect:/session/login";
		}
	}
	
	@GetMapping("/session/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		System.out.println("ログアウト実行");
		return "redirect:/";
	}

	
	
//	@PostMapping("session/login")
//	public String doLogin(LoginForm form) {
//		System.out.println(form.getUserId());
//		System.out.println(form.getPassword());
//		return "redirect:/session/login";
//	}
}
