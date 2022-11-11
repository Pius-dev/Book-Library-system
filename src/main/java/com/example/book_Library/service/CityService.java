package com.example.book_Library.service;

import com.example.book_Library.dto.requestDto.CityRequestDto;
import com.example.book_Library.dto.responseDto.CityResponseDto;
import com.example.book_Library.model.City;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CityService {
    public City addCity(CityRequestDto cityRequestDto);
    public List<City> getCities();
    public City getCity(Long cityId);
    public City deleteCity(Long cityId);
    public City editCity(Long cityId, CityRequestDto cityRequestDto);
}
