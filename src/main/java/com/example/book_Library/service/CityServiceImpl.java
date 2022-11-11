package com.example.book_Library.service;

import com.example.book_Library.dto.requestDto.CityRequestDto;
import com.example.book_Library.model.City;
import com.example.book_Library.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    private CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }


    @Override
    public City addCity(CityRequestDto cityRequestDto) {
        City city = new City();
        city.setName(cityRequestDto.getName());
        return cityRepository.save(city);
    }

    @Override
    public List<City> getCities() {
        List<City> cities = new ArrayList<>();
        cityRepository.findAll().forEach(cities::add);
        return cities;

    }

    @Override
    public City getCity(Long cityId) {
        return cityRepository.findById(cityId).orElseThrow(() ->
                new IllegalArgumentException("City with id " + cityId + "not found")
                );
    }

    @Override
    public City deleteCity(Long cityId) {
        City city = new City();
        cityRepository.delete(city);
        return city;
    }
    @Transactional
    @Override
    public City editCity(Long cityId, CityRequestDto cityRequestDto) {
        City cityToEdit = getCity(cityId);
        cityToEdit.setName(cityRequestDto.getName());
        return cityToEdit;
    }


}
