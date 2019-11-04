package br.com.report.controller;

import br.com.report.entity.User;
import br.com.report.service.impl.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;


    @ApiOperation(value = "Finds a user by its id")
    @GetMapping("/user/{id}")
    public Optional<User> findById(@PathVariable(value = "id") long id){
        return userService.findById(id);
    }

    @ApiOperation(value = "Return a list with all the users")
    @GetMapping("/users")
    public List<User> findAll(){
        return userService.findAll();
    }

    @ApiOperation(value = "Modify user on / off status")
    @PutMapping("/user")
    public void changeStatus(@RequestBody User user){
         userService.changeStatus(user);
    }

}
