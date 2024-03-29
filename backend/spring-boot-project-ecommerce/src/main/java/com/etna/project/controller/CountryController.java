package com.etna.project.controller;

import com.etna.project.entity.Country;
import com.etna.project.exception.CustomResourceNotFoundException;
import com.etna.project.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Country> getList(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        return countryService.getList(page, limit);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Country> getOneById(@PathVariable("id") Integer id) {
        Country country = countryService.getOneById(id);
        return new ResponseEntity(country, HttpStatus.OK);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Country> createCountry(@RequestBody Country country) {
        country = countryService.create(country);
        return new ResponseEntity<>(country, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Country> updateCountry(@PathVariable("id") Integer id, @RequestBody Country country) {
        country = countryService.update(id, country);
        return new ResponseEntity<>(country, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Boolean deleteCountry(@PathVariable("id") Integer id) {
        return countryService.delete(id);
    }

}
