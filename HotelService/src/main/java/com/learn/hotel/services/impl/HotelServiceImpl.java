package com.learn.hotel.services.impl;

import com.learn.hotel.entites.Hotel;
import com.learn.hotel.exceptions.ResourceNotFoundException;
import com.learn.hotel.repositories.HotelRepository;
import com.learn.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel createHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotel(String id) {
        return hotelRepository.findById(id).orElseThrow( ()-> new ResourceNotFoundException("Hotel Not found with id: "+id) );
    }
}
