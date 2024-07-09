package com.learn.hotel.repositories;

import com.learn.hotel.entites.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HotelRepository extends JpaRepository<Hotel, String> {

}
