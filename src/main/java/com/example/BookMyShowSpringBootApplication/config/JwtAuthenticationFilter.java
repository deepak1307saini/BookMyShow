package com.example.BookMyShowSpringBootApplication.config;

import com.example.BookMyShowSpringBootApplication.service.impl.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {

         final String authHeader=request.getHeader("Authorization");
         final String jwt;
         final String userEmail;

         if(authHeader==null || !authHeader.startsWith("Bearer")){
             filterChain.doFilter(request,response);
             return;
         }

         jwt=authHeader.substring(7);
         try {
             userEmail = jwtService.extractUsername(jwt);
         } catch(RuntimeException ex) {
             Map<String,String> mp=new HashMap<>();
             mp.put("error",ex.getMessage());
             ObjectMapper objectMapper = new ObjectMapper();
             response.setStatus(HttpStatus.UNAUTHORIZED.value());
             response.setContentType(MediaType.APPLICATION_JSON_VALUE);
             objectMapper.writeValue(response.getWriter(), mp);
             return;
         }
         if (userEmail!=null && SecurityContextHolder.getContext().getAuthentication()==null){
             UserDetails userDetails=this.userDetailsService.loadUserByUsername(userEmail);

             if (jwtService.isTokenValid(jwt,userDetails)){
                 UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(
                         userDetails,
                         null,
                         userDetails.getAuthorities()
                 );
                 authToken.setDetails(
                         new WebAuthenticationDetailsSource().buildDetails(request)
                 );

                 SecurityContextHolder.getContext().setAuthentication(authToken);
             }
         }
         filterChain.doFilter(request,response);

    }
}
