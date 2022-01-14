package com.biswa.app.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biswa.app.util.CookieUtil;

@Controller
public class ResourceController {

	private static final String jwtTokenCookieName = "JWT-TOKEN";

	@RequestMapping("/")
	public String home() {
		return "redirect:/protected-resource";
	}

	@RequestMapping("/protected-resource")
	public String protectedResource() {
		return "protected-resource";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletResponse httpServletResponse) {
		CookieUtil.clear(httpServletResponse, jwtTokenCookieName);
		return "redirect:/";
	}
}
