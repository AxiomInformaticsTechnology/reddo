package com.aidos.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

	@RequestMapping("/greetings")
	public @ResponseBody Map<String, String> redirect(Principal principal) {
		Map<String, String> response = new HashMap<String, String>();
		response.put("user", principal.getName());
		response.put("message", "Hello, " + principal.getName() + "!");
		return response;
	}

}
