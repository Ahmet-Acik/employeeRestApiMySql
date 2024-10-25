package com.ahmet.EmployManagementSystem.controller;

import com.ahmet.EmployManagementSystem.service.MyUserDetailService;
import com.ahmet.EmployManagementSystem.webtoken.JwtService;
import com.ahmet.EmployManagementSystem.webtoken.LoginForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ContentController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private MyUserDetailService userDetailService;

    @Operation(summary = "Handle welcome page", description = "Handle welcome page")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Welcome page")
    })
    @GetMapping("/home")
    public String handleWelcome() {
        return "home";
    }

    @Operation(summary = "Handle admin home page", description = "Handle admin home page")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Admin home page")
    })
    @GetMapping("/admin/home")
    public String handleAdminHome() {
        return "home_admin";
    }

    @Operation(summary = "Handle user home page", description = "Handle user home page")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User home page")
    })
    @GetMapping("/user/home")
    public String handleUserHome() {
        return "home_user";
    }

    @Operation(summary = "Handle login page", description = "Handle login page")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login page")
    })
    @GetMapping("/login")
    public String handleLogin() {
        return "custom_login";
    }

    @Operation(summary = "Authenticate and get token", description = "Authenticate and get token")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Token generated"),
            @ApiResponse(responseCode = "401", description = "Invalid username or password"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
@PostMapping("/authenticate")
public ResponseEntity<String> authenticateAndGetToken(@RequestBody LoginForm loginForm) {
    try {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginForm.username(), loginForm.password())
        );

        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(userDetailService.loadUserByUsername(loginForm.username()));
            return ResponseEntity.ok(token);
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    } catch (AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }
}
}
