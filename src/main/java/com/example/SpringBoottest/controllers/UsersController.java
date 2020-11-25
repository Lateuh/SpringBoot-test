package com.example.SpringBoottest.controllers;

import com.example.SpringBoottest.models.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.SpringBoottest.repositories.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {
    @Autowired
    private UserRepository userRepository;

    //renvoie liste de tous les utilisateurs
    @GetMapping
    public List<User> getUsers(){
        System.out.println("LAAAAAAAASIONJ?OINDOIUANDOUINAZODINAZOINDOINDOIEJNFUIO");
        return userRepository.findAll();
    }


    @GetMapping
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable long id){
        return userRepository.getOne(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User postUser(@RequestBody final User user){
        return userRepository.saveAndFlush(user);
    }


    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable long id){
        userRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public User update(@PathVariable long id, @RequestBody User user){
        // TO DO

        User existingUser = userRepository.getOne(id);
        BeanUtils.copyProperties(user,existingUser,"user_id");
        return userRepository.saveAndFlush(existingUser);
    }
}
