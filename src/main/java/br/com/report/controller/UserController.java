package br.com.report.controller;

import br.com.report.entity.User;
import br.com.report.service.impl.UserService;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;


    @ApiOperation(value = "Finds a user by its id")
    @GetMapping("/user/{id}")
    public Optional<User> findById(@PathVariable(value = "id") long id) throws NotFoundException {
        Optional<User> user = userService.findById(id);
        user.orElseThrow(()-> new NotFoundException("Not found user with id: " + id));
        return user;
    }

    @ApiOperation(value = "Return a list with all the users")
    @GetMapping("/user")
    public List<User> findAll() throws NotFoundException {
        List<User> users = userService.findAll();
        if(users.isEmpty())
            throw new NotFoundException("No users!");
        return users;
    }

    @ApiOperation(value = "Modify user on / off status")
    @PutMapping("/user/{id}")
    public void changeStatus(@RequestBody User user, @PathVariable("id") Long id) throws NotFoundException {
        Optional<User> findUser = userService.findById(id);
        findUser.orElseThrow(()-> new NotFoundException("Not found user with id: " + id));
        userService.changeStatus(user);

    }
}
