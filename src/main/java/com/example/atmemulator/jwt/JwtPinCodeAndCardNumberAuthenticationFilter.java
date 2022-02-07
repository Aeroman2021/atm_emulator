package com.example.atmemulator.jwt;


import com.example.atmemulator.service.CreditCardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;


public class JwtPinCodeAndCardNumberAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;
    private final CreditCardService creditCardService;

    public JwtPinCodeAndCardNumberAuthenticationFilter(AuthenticationManager authenticationManager,
                                                       JwtConfig jwtConfig,
                                                       SecretKey secretKey,
                                                       CreditCardService creditCardService) {
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
        this.creditCardService = creditCardService;
    }
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            PinAndCardNumberAuthenticationRequest authenticationRequest =
                    new ObjectMapper().readValue(request.getInputStream(),
                            PinAndCardNumberAuthenticationRequest.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken
                    (authenticationRequest.getCardNumber(), authenticationRequest.getPinCode());

            Authentication authenticate = authenticationManager.authenticate(authentication);
            if(authenticate.isAuthenticated())
                creditCardService.resetLoginAttempt(authenticationRequest.getCardNumber());
            return authenticate;
        } catch (IOException e) {
            throw new RuntimeException( e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
                .signWith(secretKey)
                .compact();
        response.addHeader(jwtConfig.getAuthorizationHeader(),
                jwtConfig.getTokenPrefix() + token);

    }

}
