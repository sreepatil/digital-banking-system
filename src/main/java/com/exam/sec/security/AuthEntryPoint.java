package com.exam.sec.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {

	@Autowired
	ObjectMapper objectMapper;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		authException.printStackTrace();
		System.out.println();
		System.out.println(request.getServletPath());
		System.out.println(request.getServletContext().getContextPath());
		System.out.println(request.getPathTranslated());
		System.out.println(request.getPathInfo());
		if (request.getContextPath() != null && request.getContextPath().equals("/error")) {
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("error", "Only admins can perform this operation.");
			String jsonErrorResp = objectMapper.writer().writeValueAsString(errorResponse);
			response.getWriter().write(jsonErrorResp);
		}

	}

}
