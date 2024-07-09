package com.learn.rating.services;

import com.learn.rating.entities.Rating;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RatingService {

    //Create
    Rating create(Rating rating);

    //get all rating
    List<Rating> getAllRating();

    //get All by userID
    List<Rating> getRatingByUserId(String userId);

    //get all by hotel

    List<Rating> getRatingByHotelId(String hotelId);

}
