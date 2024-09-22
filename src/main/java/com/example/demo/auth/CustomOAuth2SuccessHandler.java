package com.example.demo.auth;

import com.example.demo.config.JwtService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomOAuth2SuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private JwtService jwtService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("Got hereee!!");
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
        String username = oauth2User.getAttribute("email");

        if (username == null) {
            // Fallback for LinkedIn if email is not directly available
            username = getLinkedInEmail(oauth2User);
        }
        String token = jwtService.generateToken(username);
        //check if user exists in the db
        //if not create a new user
        response.sendRedirect(System.getProperty("APP_CLIENT_URL") + "?token=" + token);
    }

    private String getLinkedInEmail(OAuth2User oauth2User) {
        return oauth2User.getAttribute("emailAddress");
    }
}
