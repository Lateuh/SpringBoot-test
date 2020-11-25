package com.example.SpringBoottest.controllers;

import com.example.SpringBoottest.models.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.SpringBoottest.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {
    @Autowired
    private UserRepository userRepository;

    //renvoie liste de tous les utilisateurs
    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        List<User> listUsers = new ArrayList<>();
        try{
            userRepository.findAll().forEach(listUsers::add);
            if(listUsers.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(listUsers,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //renvoie l'utilisateur avec l'id entré dans URL
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id){
        Optional<User> _user = userRepository.findById(id);
        if (_user.isPresent()){
         return new ResponseEntity<>(_user.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    //renvoie liste de tous les utilisateurs
    @GetMapping("/rand")
    public ResponseEntity<User> getUserRand(){
        List<User> listUsers = new ArrayList<>();
        try{
            userRepository.findAll().forEach(listUsers::add);
            if(listUsers.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            int randomIndex = (int) (Math.random() * listUsers.size());
            User userRand = listUsers.get(randomIndex);
            return new ResponseEntity<>(userRand,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ajoute un nouveau user dans la bdd
    @PostMapping
    public ResponseEntity<User> postUser(@RequestBody final User user){
        return new ResponseEntity<>(userRepository.saveAndFlush(user),HttpStatus.CREATED);
    }

    //supprime l'user d'après son id
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id){
        try{
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //maj l'user avec l'id donné
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable("id") long id, @RequestBody User user){
        if (user.getFirst_name().isEmpty() || user.getLast_name().isEmpty() ||
                user.getEmail().isEmpty() || user.getPassword().isEmpty() ||
                user.getPhone_number().isEmpty()){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<User> existingUser = userRepository.findById(id);
        if(existingUser.isPresent()){
            User _user = existingUser.get();
            _user.setFirst_name(user.getFirst_name());
            _user.setLast_name(user.getLast_name());
            _user.setEmail(user.getEmail());
            _user.setPassword(user.getPassword());
            _user.setPhone_number(user.getPhone_number());
            _user.setLocations(user.getLocations());
            return new ResponseEntity<>(userRepository.saveAndFlush(_user),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //BeanUtils.copyProperties(user,existingUser,"user_id"); // utilisé dans le cours
    }
}
