package com.trainibit.labs.spring_boot_hibernate_crud.controller;

import com.trainibit.labs.spring_boot_hibernate_crud.dto.UserRequest;
import com.trainibit.labs.spring_boot_hibernate_crud.model.Profile;
import com.trainibit.labs.spring_boot_hibernate_crud.model.User;
import com.trainibit.labs.spring_boot_hibernate_crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest){

        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());


        Profile profile = new Profile();
        profile.setBio(userRequest.getProfileBio());
        profile.setUser(user);
        user.setProfile(profile);

        userService.saveUserWithProfile(user);
        return new ResponseEntity<>("Usuario creado correctamente", HttpStatus.CREATED);
    }


    @GetMapping("/getUser/{id}")
    public User getUser(@PathVariable int id){
        Profile profile = new Profile();
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user,HttpStatus.OK).getBody(); // 200


    }
}

