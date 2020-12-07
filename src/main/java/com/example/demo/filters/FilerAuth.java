package com.example.demo.filters;

import com.example.demo.services.JWTservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class FilerAuth extends OncePerRequestFilter {

    @Autowired
    JWTservice jwTservice;

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
//get JWT from header
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;
        //get JWT and Username
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwTservice.extractUsername(jwt);
        }

        //context contain the information of currently user (principal)
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            //get user details
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            //test if token is valid
            if (jwTservice.validateToken(jwt, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        //proceeding to the next element in the chain
        chain.doFilter(request, response);
    }
}
