package com.hotel.hotelmanagement.controller;

import com.hotel.hotelmanagement.dto.UserRequestDto;
import com.hotel.hotelmanagement.dto.UserResponseDto;
import com.hotel.hotelmanagement.model.User;
import com.hotel.hotelmanagement.service.UserService;
import org.springframework.http.HttpStatus;
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
    @GetMapping
    public ResponseEntity<UserResponseDto> getAllUsers(){
         List<User> users= userService.getUsers();
         UserResponseDto response = new UserResponseDto(200,users);
         return ResponseEntity.ok(response);
    }

    //get user by id
    @GetMapping("/{userid}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long userid){
        User existingUser = userService.getById(userid);
        UserResponseDto response = new UserResponseDto(200,existingUser);
        return ResponseEntity.ok(response);
    }

    //get loyalty points by id
    @GetMapping("/{userid}/loyalty")
    public ResponseEntity<UserResponseDto> getUserLoyalty(@PathVariable Long userid){
        Integer loyalty = userService.getLoyalty(userid);
        UserResponseDto response = new UserResponseDto(200,loyalty);
        return ResponseEntity.ok(response);

    }

    //create new user
    @PostMapping()
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto dto){
        User user= new User(dto.getUsername(), dto.getEmail(), dto.getPhone(),dto.getType());
        User newUser = userService.saveUser(user);
        UserResponseDto response = new UserResponseDto(201,newUser);
        return ResponseEntity.ok(response);
    }

//    //update type
//    @PutMapping("/{userid}/type")
//    public ResponseEntity<User> updateType(@PathVariable Long userid,@RequestParam String type){
//        return ResponseEntity.ok(userService.updateType(userid,type));
//    }

    //update loyalty
    @PutMapping("/{userid}/loyalty")
    public ResponseEntity<UserResponseDto> updateLoyalty(@PathVariable Long userid,@RequestParam Integer loyalty){
        User user =  userService.updateLoyalty(userid,loyalty);
        UserResponseDto response = new UserResponseDto(200,user);
        return ResponseEntity.ok(response);
    }

    //delete user by id
    @DeleteMapping("/{userid}")
    public ResponseEntity<UserResponseDto> deleteUser(@PathVariable Long userid){
        userService.deleteUser(userid);
        UserResponseDto response = new UserResponseDto(204,"User deleted");
        return ResponseEntity.ok(response);
    }
}

