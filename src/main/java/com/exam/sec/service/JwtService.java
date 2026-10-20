package com.exam.sec.service;

import java.util.Date;
import java.util.HashMap;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private final static String KEY = "ThisIsASecretKeyThisIsASecretKeyThisIsASecretKey";
	private final static long EXPIRY = 24 * 60 * 60 * 1000l;

	public String generateToken(UserDetails user) {
		return Jwts.builder().signWith(generateSecretKey()).claims(new HashMap<>()).subject(user.getUsername())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + EXPIRY)).compact();
	}

	public Claims extractAllClaims(String token) {
		return Jwts.parser().verifyWith(generateSecretKey()).build().parseSignedClaims(token).getPayload();
	}

	public String extractUsername(String token) {
		return extractAllClaims(token).getSubject();
	}

	public Date extractExpiration(String token) {
		return extractAllClaims(token).getExpiration();
	}

	public boolean validateToken(String token, UserDetails user) {
		Date expiration = extractExpiration(token);
		String username = extractUsername(token);
		return username.equals(user.getUsername()) && expiration.after(new Date(System.currentTimeMillis()));
	}

	private SecretKey generateSecretKey() {
		return Keys.hmacShaKeyFor(KEY.getBytes());
	}

}
