package com.yanfei.springFrame.Config;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private static final PathMatcher pathMatcher = new AntPathMatcher();
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		try {
			if (isProtectedUrl(request)) {
				String token = request.getHeader("Authorization");
				// 检查jwt令牌, 如果令牌不合法或者过期, 里面会直接抛出异常, 下面的catch部分会直接返回
				Map<String, Object> info = JwtUtil.validateToken(token);
				
				String requestURI = request.getRequestURI();
				if ((requestURI.contains("action") || requestURI.contains("Action"))
						&& (requestURI.contains("create") || requestURI.contains("delete") || requestURI.contains("modify"))) {
					if (!(boolean) info.get("isAdmin")) {
						throw new IllegalStateException("权限不足");
					}
				}
				ParameterRequestWrapper requestWrapper = new ParameterRequestWrapper(request);
				requestWrapper.addParameter("currentUserId", (int) info.get("userId"));
				filterChain.doFilter(requestWrapper, response);
				
			} else {
				filterChain.doFilter(request, response);
			}
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
			return;
		}
		
	}
	
	private boolean isProtectedUrl(HttpServletRequest request) {
		// 不需要token的接口
		String requestURI = request.getRequestURI();
		if (requestURI.contains("user/login") || requestURI.contains("user/adminLogin") || requestURI.contains("swagger") || requestURI.contains("/v2")) {
			return false;
		}
		// 其余url
		return pathMatcher.match("/**", request.getServletPath());
	}
	
}
