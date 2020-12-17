package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SpringSecurityController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String LoginPage() {

		return "login";

	}

	@RequestMapping(value = "/403")
	public String error403() {
		return "/error/403";
	}
}
