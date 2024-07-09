package com.learn.user.controllers;

import com.learn.user.entities.User;
import com.learn.user.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1 = userService.saveUser(user);
        //return new ResponseEntity<>(user1, HttpStatus.CREATED);
       return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> users= userService.getAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/{userId}")
    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUser(@PathVariable String userId){
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    public  ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
        logger.info("Falllback is executed because some service is down", ex.getMessage());

        User dummy = User.builder().name("Dummy").email("Dummy@gmail.com").
                userId("23344").about("This is dummy data for fallback methos").build();
        return new ResponseEntity<>(dummy, HttpStatus.OK);
    }
}
