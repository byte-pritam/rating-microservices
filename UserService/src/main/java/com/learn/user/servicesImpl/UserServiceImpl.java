package com.learn.user.servicesImpl;

import com.learn.user.entities.Hotel;
import com.learn.user.entities.Rating;
import com.learn.user.entities.User;
import com.learn.user.exceptions.ResourceNotFoundException;
import com.learn.user.external.services.HotelService;
import com.learn.user.repositories.UserRepository;
import com.learn.user.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {

        String randomId = UUID.randomUUID().toString();
        user.setUserId(randomId);
        return userRepository.save(user);

    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow( () -> new ResourceNotFoundException("User not found with given userId :"+ userId));
        Rating[] ratings = restTemplate.getForObject("http://RATING-SERVICE/rating/user/"+user.getUserId(), Rating[].class);
        logger.info("rating array : {}", ratings[0]);

        List<Rating> list = Arrays.stream(ratings).toList();

        List<Rating> ratingList = list.stream().map(rating->{
           // ResponseEntity<Hotel> hotel = restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/"+rating.getHotelId(), Hotel.class);
            //Hotel hotel1 = hotel.getBody();
            // using feign client
            Hotel hotel1 = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel1);
            return rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingList);
        return user;
    }
}
