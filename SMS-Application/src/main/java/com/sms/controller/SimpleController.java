package com.sms.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SimpleController {
	
	@Value("${spring.application.name}")
    String appName;
	
	@GetMapping("/header")
    public String homePage(Model model) {
        //model.addAttribute("appName", appName);
        return "fragments/admin-header";
    }
}
