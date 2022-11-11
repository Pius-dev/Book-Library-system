package com.example.book_Library.controller;


import com.example.book_Library.dto.requestDto.ZipcodeRequestDto;
import com.example.book_Library.dto.responseDto.AuthorResponseDto;
import com.example.book_Library.model.Zipcode;
import com.example.book_Library.service.ZipcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zipcode")
public class ZipcodeController {
    private ZipcodeService zipcodeService;

    @Autowired
    public ZipcodeController(ZipcodeService zipcodeService) {
        this.zipcodeService = zipcodeService;
    }

    @PostMapping("/add")
    public ResponseEntity<Zipcode> addZipcode(@RequestBody final ZipcodeRequestDto ZipcodeRequestDto) {
        Zipcode zipcode = zipcodeService.addZipcode(ZipcodeRequestDto);
        return new ResponseEntity<>(zipcode, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Zipcode> getZipcodeById(@PathVariable final Long id) {
        Zipcode zipcode = zipcodeService.getZipcode(id);
        return new ResponseEntity<>(zipcode, HttpStatus.OK);

    }

    @GetMapping("/getall")
    public ResponseEntity<List<Zipcode>> getZipcodes() {
        List<Zipcode> zipcodes = zipcodeService.getZipcodes();
        return new ResponseEntity<List<Zipcode>>(zipcodes, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Zipcode> deleteZipcode(@PathVariable final Long id) {
        Zipcode zipcode = zipcodeService.deleteZipcode(id);
        return new ResponseEntity<>(zipcode, HttpStatus.OK);

    }

    @PutMapping ("/edit/{id}")
    public ResponseEntity<Zipcode> editZipcode(@RequestBody final ZipcodeRequestDto ZipcodeRequestDto,
                                         @PathVariable final Long id) {
        Zipcode zipcode = zipcodeService.editZipcode(id, ZipcodeRequestDto);
        return new ResponseEntity<>(zipcode, HttpStatus.OK);

    }

    @PostMapping("/addCity/{cityId}/toZipcode/{zipcodeId}")
    public ResponseEntity<Zipcode> addZipcode(@PathVariable final Long zipcodeId,
                                                        @PathVariable final Long cityId) {
        Zipcode zipcode = zipcodeService.addCityToZipcode(zipcodeId, cityId);
        return new ResponseEntity<>(zipcode, HttpStatus.OK);

    }

    @DeleteMapping("/deleteCity/{id}")
    public ResponseEntity<Zipcode> removeZipcodeFromAuthor(@PathVariable final Long id) {
        Zipcode zipcode = zipcodeService.removeCityFromZipcode(id);
        return new ResponseEntity<>(zipcode, HttpStatus.OK);

    }
}

