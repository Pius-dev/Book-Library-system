package com.example.book_Library.repository;

import com.example.book_Library.model.Zipcode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZipcodeRespository extends CrudRepository<Zipcode, Long> {

}
