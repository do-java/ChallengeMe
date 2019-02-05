package com.dj.cm.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

	@Autowired
	private Environment environment;

	@GetMapping
	public String main(Model model) {
		final String mode = environment.getActiveProfiles().length > 0 ? environment.getActiveProfiles()[0] : "development";

		model.addAttribute("mode", mode);
		return "index";
	}
}
