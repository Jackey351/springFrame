package com.yanfei.springFrame.Config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	
	static final String SECRET = "YANFEI";
	
	private static final long EXPIRE_TIME = 1 * 24 * 60 * 60 * 1000;// 1天过期
	
	public static String generateToken(int userId, boolean isAdmin) {
		HashMap<String, Object> map = new HashMap<>();
		// you can put any data in the map
		map.put("userId", userId);
		map.put("isAdmin", isAdmin);
		String jwt = Jwts.builder().setClaims(map).setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME)).signWith(SignatureAlgorithm.HS512, SECRET).compact();
		return jwt;
	}
	
	@SuppressWarnings({ "deprecation", "static-access" })
	public static Map<String, Object> validateToken(String token) {
		try {
			Map<String, Object> body = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
			int userId = (int) body.get("userId");
			
			return body;
		} catch (Exception e) {
			throw new IllegalStateException("Token已失效，请重新登陆。");
		}
	}
}
