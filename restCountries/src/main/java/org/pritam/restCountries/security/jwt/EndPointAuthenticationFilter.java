package org.pritam.restCountries.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
@Component
public class EndPointAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
		String authorization = request.getHeader("Authorization");
		if(authorization==null)
			throw new Exception("There is no authorization header present in the request.");
		else if(!authorization.startsWith("Bearer "))
			throw new Exception("Token format is invalid.");
			String token = authorization.substring(7);
			String username = jwtService.extractUsername(token);
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				if(jwtService.validateToken(token, userDetails)) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),null,userDetails.getAuthorities());
					usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}
		}
		catch(Exception e) {
			if(request.getRequestURI().contains("/v3.1") && !request.getRequestURI().equals("/v3.1/generateToken"))
			{
				response.setStatus(401);
				response.setContentType("application/json");
				response.getOutputStream().println("{"
						+ "\"errorCode\":401,"
						+ "\"description\":\"" + e.getMessage() +"\""
						+ "}");
			}
		}
		filterChain.doFilter(request, response);
	}

}
