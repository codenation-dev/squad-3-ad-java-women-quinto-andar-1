package br.com.report.controller;

import br.com.report.entity.User;
import br.com.report.service.exception.UserNotFoundException;
import br.com.report.service.impl.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public User addUser(@RequestBody User user) {
        try {
            return userService.addUser(user);
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/user/{id}")
    public User findById(@PathVariable(value = "id") long id) {
            return userService.findById(id)
                    .orElseThrow(()-> new UserNotFoundException(id));
    }

    @GetMapping("/users")
    public List<User> findAll() {
        try{
            return userService.findAll();
        }catch (Exception e){
            throw e;
        }
    }

    @PutMapping("/user")
    public void changeStatus(@RequestBody User user) {
       try{
           userService.changeStatus(user);
       }catch (Exception e) {
           throw e;
       }
    }

}
