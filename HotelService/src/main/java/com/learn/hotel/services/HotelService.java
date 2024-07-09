package com.learn.hotel.services;

import com.learn.hotel.entites.Hotel;

import java.util.List;

public interface HotelService {

    Hotel createHotel(Hotel hotel);

    List<Hotel> getAllHotels();

    Hotel getHotel(String id);
}
