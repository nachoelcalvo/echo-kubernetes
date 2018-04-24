package com.example.echo;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
public class JWTFilter implements Filter {

	private static final String AUTHORIZATION = "authorization";

	@Override public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		String token = ((HttpServletRequest) servletRequest).getHeader(AUTHORIZATION);
		if(token != null){
			processToken(token);
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}

	private void processToken(String token) {
		JWT tokenJWT = JWT.decode(token);
		Map<String, Claim> claims = tokenJWT.getClaims();

		log.info("Token Claims {}", claims.keySet());
	}

	@Override public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override public void destroy() {

	}
}
