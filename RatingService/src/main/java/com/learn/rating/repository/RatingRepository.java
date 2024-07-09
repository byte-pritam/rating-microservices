package com.learn.rating.repository;

import com.learn.rating.entities.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface RatingRepository extends MongoRepository<Rating, String> {

    List<Rating> findByUserId(String userId);
    List<Rating> findByHotelId(String hotelId);
}
