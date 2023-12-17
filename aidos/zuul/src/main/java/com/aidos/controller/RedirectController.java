package com.aidos.controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectController {

	@RequestMapping("/")
	public void redirect(Principal principal, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("https://localhost");
	}

}
