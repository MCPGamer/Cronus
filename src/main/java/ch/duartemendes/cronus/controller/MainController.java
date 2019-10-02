package ch.duartemendes.cronus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ch.duartemendes.cronus.domain.LoginData;

@Controller
public class MainController {

	@GetMapping("/")
	public String index(Model model) {
		return "index";
	}
	
	@GetMapping("signIn")
	public String loginGET(Model model) {
		model.addAttribute("LoginData", new LoginData());
		return "loginForm";
	}
}