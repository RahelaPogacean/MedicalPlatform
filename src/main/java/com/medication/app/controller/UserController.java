package com.medication.app.controller;

import com.medication.app.dto.LoginDTO;
import com.medication.app.dto.PatientDTO;
import com.medication.app.dto.RegisterDTO;
import com.medication.app.dto.UserDTO;
import com.medication.app.entity.hospital.User;
import com.medication.app.service.hospital.PatientService;
import com.medication.app.service.hospital.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
//@CrossOrigin("http://localhost:4200")
@CrossOrigin("*")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private PatientService patientService;



    @PostMapping(value = "/login")
    public ResponseEntity<LoginDTO> loginUser(@RequestBody LoginDTO loginDTO) {

        LoginDTO response = userService.login(loginDTO.getName(), loginDTO.getPassword());
        if (response == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<RegisterDTO> registerUser(@RequestBody RegisterDTO registerDTO) {

        RegisterDTO response = userService.register(registerDTO.getName(), registerDTO.getPassword(), registerDTO.getBirthdate(),
                registerDTO.getGender(), registerDTO.getAddress(), registerDTO.getRole());


        if (response == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
