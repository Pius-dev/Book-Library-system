package com.example.book_Library.service;

import com.example.book_Library.dto.requestDto.ZipcodeRequestDto;
import com.example.book_Library.model.City;
import com.example.book_Library.model.Zipcode;
import com.example.book_Library.repository.ZipcodeRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ZipcodeServiceImpl implements ZipcodeService {

    private ZipcodeRespository zipcodeRespository;
    private CityService cityService;

    @Autowired
    public ZipcodeServiceImpl(ZipcodeRespository zipcodeRespository, ZipcodeService zipcodeService, CityService cityService) {
        this.zipcodeRespository = zipcodeRespository;
        this.cityService = cityService;
    }
    @Transactional
    @Override
    public Zipcode addZipcode(ZipcodeRequestDto zipcodeRequestDto) {
        Zipcode zipcode = new Zipcode();
        zipcode.setName(zipcodeRequestDto.getName());
        if (zipcodeRequestDto.getCityId() == null){
            return zipcodeRespository.save(zipcode);
        }
        City city = cityService.getCity(zipcodeRequestDto.getCityId());
        return zipcodeRespository.save(zipcode);

    }

    @Override
    public List<Zipcode> getZipcodes() {
        return StreamSupport
                .stream(zipcodeRespository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Zipcode getZipcode(Long zipcodeId) {
        return zipcodeRespository.findById(zipcodeId).orElseThrow(() ->
                new IllegalArgumentException("Zipcode with id " + zipcodeId + "not found")
        );
    }

    @Override
    public Zipcode deleteZipcode(Long zipcodeId) {
        Zipcode zipcode = getZipcode(zipcodeId);
        zipcodeRespository.delete(zipcode);
        return zipcode;
    }
    @Transactional
    @Override
    public Zipcode editZipcode(Long zipcodeId, ZipcodeRequestDto zipcodeRequestDto) {
        Zipcode zipcodeToEdit = getZipcode(zipcodeId);
        zipcodeToEdit.setName(zipcodeRequestDto.getName());
        if (zipcodeRequestDto.getCityId() != null){
            return zipcodeToEdit;
        }
        City city  = cityService.getCity(zipcodeRequestDto.getCityId());
        zipcodeToEdit.setCity(city);
        return zipcodeToEdit;
    }

    @Transactional
    @Override
    public Zipcode addCityToZipcode(Long zipcodeId, Long cityId) {
        Zipcode zipcode = getZipcode(zipcodeId);
        City city = cityService.getCity(cityId);

        if (Objects.nonNull(zipcode.getCity())) {
            throw new IllegalArgumentException("Zipcode already has a city");
        }
        zipcode.setCity(city);
        return zipcode;
    }

    @Transactional
    @Override
    public Zipcode removeCityFromZipcode(Long zipcodeId) {
        Zipcode zipcode = getZipcode(zipcodeId);

        if (!Objects.nonNull(zipcode.getCity())) {
            throw new IllegalArgumentException("Zipcode does not have a city");
        }
        zipcode.setCity(null);
        return zipcode;
    }
}
