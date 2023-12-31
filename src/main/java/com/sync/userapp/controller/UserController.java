package com.sync.userapp.controller;

import com.sync.userapp.dto.ResponseDTO;
import com.sync.userapp.dto.UserDTO;
import com.sync.userapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/status")
    String serviceStatus() {
        return "Service is up";
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> registerUser(@RequestBody UserDTO userDTO) {
        userDTO.setCreatedOn(new Date());
        String message = userService.registerUser(userDTO);
        ResponseDTO response = new ResponseDTO();
        response.setResponseMessage(message);
        response.setUserId(userDTO.getUserId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(value = "/userAvailable/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> isUserIdAvailable(@PathVariable("userId") String userId) {
        String message = userService.checkUserIdAvailable(userId);
        ResponseDTO response = new ResponseDTO();
        response.setResponseMessage(message);
        response.setUserId(userId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
