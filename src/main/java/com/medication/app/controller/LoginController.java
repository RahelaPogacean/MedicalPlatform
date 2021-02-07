package com.medication.app.controller;


import com.medication.app.dto.AuthenticationDto;
import com.medication.app.dto.LoginDTO;
import com.medication.app.dto.ResponseMessageDTO;
import com.medication.app.dto.UserDTO;
import com.medication.app.security.jwt.JwtProvider;
import com.medication.app.security.jwt.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
@CrossOrigin("*")
public class LoginController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    private AuthenticationDto authentication;

//    @PostMapping("/signin")
//    public ResponseEntity<JwtResponse> login(@RequestBody LoginDTO loginDTO){
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginDTO.getName(), loginDTO.getPassword())
//        );
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        String jwt = jwtProvider.generateJwtToken(authentication);
//        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
//
//        System.out.println(userDetails.getAuthorities());
//
//        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
//    }

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getName(), loginDTO.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();

        System.out.println(userDetails.getAuthorities());

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDTO userDTO){
        if (authentication.existsUserByUsername(userDTO.getName())){
            return new ResponseEntity<>(new ResponseMessageDTO("Fail -> Username already taken"), HttpStatus.BAD_REQUEST);
        }

//        if (authentication.existUserByRole(userDTO.getRole())){
//            return new ResponseEntity<>(new ResponseMessageDTO("Fail -> Email address already taken"), HttpStatus.BAD_REQUEST);
//        }

        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        authentication.registerUser(userDTO);

        return new ResponseEntity<>(new ResponseMessageDTO("User registered successfully!"), HttpStatus.OK);
    }
}
