package com.rvss.rolebasedaccess.contoller;

import com.rvss.rolebasedaccess.dto.AuthRequest;
import com.rvss.rolebasedaccess.dto.AuthResponse;
import com.rvss.rolebasedaccess.entity.Role;
import com.rvss.rolebasedaccess.entity.User;
import com.rvss.rolebasedaccess.service.CustomUserDetailsService;
import com.rvss.rolebasedaccess.security.JwtUtil;

import com.rvss.rolebasedaccess.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "info/rbac/api/v1",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE,
        method = {RequestMethod.GET, RequestMethod.POST})
@RestController

@RequiredArgsConstructor
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;
    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody AuthRequest request) {
        userService.registerUser(request); // You must map AuthRequest to your User entity inside service
        return ResponseEntity.ok(request.getUsername() + " registered successfully as " + request.getRole());
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userService.getByUsername(request.getUsername());
        String token = jwtUtil.generateToken(user);

        /*List<String> roleNames = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList());*/

        String rolesCommaSeparated = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.joining(","));


        return ResponseEntity.ok(AuthResponse.builder()
                       .message(user.getUsername())
                       .role(rolesCommaSeparated)
                       .token(token)
                        .build());
    }
    @GetMapping("/getCurrentUser")
    public ResponseEntity<String> getCurrentUser(Authentication authentication) {
        System.out.println("Authentication: " + authentication);
        System.out.println("Authorities: " + authentication.getAuthorities());
        User user = (User) authentication.getPrincipal(); // This may fail if principal is just username string.
        return ResponseEntity.ok("Hello, " + user.getUsername() + "! You are logged in as " + authentication.getAuthorities());
    }

}
