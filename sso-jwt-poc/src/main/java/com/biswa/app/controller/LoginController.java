package com.biswa.app.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biswa.app.util.CookieUtil;
import com.biswa.app.util.JwtUtil;

@Controller
public class LoginController {

	 private static final String jwtTokenCookieName = "JWT-TOKEN";
	    private static final String signingKey = "signingKey";
	    private static final Map<String, String> credentials = new HashMap<>();

	    public LoginController() {
	        credentials.put("biswa", "biswa");
	        credentials.put("sahoo", "sahoo");
	    }

	    @RequestMapping("/")
	    public String home(){
	        return "redirect:/login";
	    }

	    @RequestMapping("/login")
	    public String login(){
	        return "login";
	    }

	    @RequestMapping(value = "login", method = RequestMethod.POST)
	    public String login(HttpServletResponse httpServletResponse, String username, String password, String redirect, Model model){
	        if (username == null || !credentials.containsKey(username) || !credentials.get(username).equals(password)){
	            model.addAttribute("error", "Invalid username or password!");
	            return "login";
	        }

	        String token = JwtUtil.generateToken(signingKey, username);
	        CookieUtil.create(httpServletResponse, jwtTokenCookieName, token, false, -1, "localhost");

	        return "redirect:" + redirect;
	    }
}
