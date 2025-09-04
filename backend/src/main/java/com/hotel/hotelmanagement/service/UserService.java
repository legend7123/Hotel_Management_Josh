package com.hotel.hotelmanagement.service;

import com.hotel.hotelmanagement.exception.UserExistsException;
import com.hotel.hotelmanagement.exception.UserNotFoundException;
import com.hotel.hotelmanagement.model.User;
import com.hotel.hotelmanagement.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    public final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    //get user by id
    public User getById(Long id){
        return userRepository.findById(id).
                orElseThrow(()-> new UserNotFoundException("User not found"));
    }

    //get all users
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    //get loyalty point by id
    public Integer getLoyalty(Long id){
        return getById(id).getLoyalty();
    }

    //create user
    public User saveUser(User user){
        if(userRepository.existsByEmail(user.getEmail())){
            throw new UserExistsException("Email exists");
        } else if (userRepository.existsByPhone(user.getPhone())) {
            throw new UserExistsException("Phone exists");
        }

        return userRepository.save(user);
    }

//    //update type
//    @Transactional
//    public User updateType(Long id,String type){
//        User user = getById(id);
//        user.setType(type);
//        return user;
//    }

    //update loyalty
    @Transactional
    public User updateLoyalty(Long id,Integer loyalty){
        User user= getById(id);
        user.setLoyalty(loyalty);
        return user;
    }

    //delete by id
    public void deleteUser(Long id){
        User user= getById(id);
        userRepository.delete(user);
    }

}
