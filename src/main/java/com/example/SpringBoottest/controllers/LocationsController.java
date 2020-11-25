package com.example.SpringBoottest.controllers;

import com.example.SpringBoottest.models.Location;
import com.example.SpringBoottest.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.SpringBoottest.repositories.LocationRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/locations")
public class LocationsController {
    @Autowired
    private LocationRepository locationRepository;

    //renvoie liste de tous les utilisateurs
    @GetMapping
    public ResponseEntity<List<Location>> getUsers(){
        List<Location> listLocations = new ArrayList<>();
        try{
            locationRepository.findAll().forEach(listLocations::add);
            if(listLocations.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(listLocations,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
