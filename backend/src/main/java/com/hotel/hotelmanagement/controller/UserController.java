package com.hotel.hotelmanagement.controller;

import com.hotel.hotelmanagement.model.User;
import com.hotel.hotelmanagement.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    public final UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }

    //get all users
    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }

    //get user by id
    @GetMapping("/{userid}")
    public ResponseEntity<User> getUserById(@PathVariable Long userid){
        return ResponseEntity.ok(userService.getById(userid));
    }

    //get loyalty points by id
    @GetMapping("/{userid}/loyalty")
    public ResponseEntity<Integer> getUserLoyalty(@PathVariable Long userid){
        return ResponseEntity.ok(userService.getLoyalty(userid));

    }

    //create new user
    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user){
        User newUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    //update type
    @PutMapping("/{userid}/type")
    public ResponseEntity<User> updateType(@PathVariable Long userid,@RequestParam String type){
        return ResponseEntity.ok(userService.updateType(userid,type));
    }

    //update loyalty
    @PutMapping("/{userid}/loyalty")
    public ResponseEntity<User> updateLoyalty(@PathVariable Long userid,@RequestParam Integer loyalty){
        return ResponseEntity.ok(userService.updateLoyalty(userid,loyalty));
    }

    //delete user by id
    @DeleteMapping("/{userid}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userid){
        userService.deleteUser(userid);
        return ResponseEntity.noContent().build();
    }
}

