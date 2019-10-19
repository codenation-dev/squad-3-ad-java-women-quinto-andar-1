package br.com.report.controller;

import br.com.report.model.User;
import br.com.report.service.UserService;
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

    @ApiOperation(value = "Return list os users")
    @GetMapping("/users")
    public List<User> userList(){
        return userService.findAll();
    }

    @ApiOperation(value = "Return one user")
    @GetMapping("/user/{id}")
    public Optional<User> user(@PathVariable(value = "id") long id){
        return userService.findById(id);
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user){
        return userService.toSave(user);
    }

    @PutMapping("/user/{id}")
    public User changeStatus(@PathVariable(value = "id") Long id, @RequestBody User user){
        user.setActive(!user.getActive());
        //userService.changeStatus(id);
        return user;
    }

    @PutMapping("/user")
    public User updateUser(@RequestBody User user){
        return userService.toSave(user);
    }

}
