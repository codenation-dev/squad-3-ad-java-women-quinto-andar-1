package br.com.report.controller;

import br.com.report.entity.User;
import br.com.report.payload.Response;
import br.com.report.service.impl.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;
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


    @ApiOperation(value = "Finds a user by its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the user", response = Response.class),
            @ApiResponse(code = 401, message = "You do not have permission to access this feature.", response = Response.class),
            @ApiResponse(code = 404, message = "User not found", response = Response.class),
            @ApiResponse(code = 500, message = "An exception was thrown", response = Response.class),
    })
    @GetMapping("/users/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id") long id) {
        Optional<User> user = userService.findById(id);
        if(user.isPresent())
            return new ResponseEntity<User>(user.get(), HttpStatus.OK);
        return new ResponseEntity<>(
                new Response(false, "Not found user with id: " + id),
                HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Return a list with all the users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the list user", response = Response.class),
            @ApiResponse(code = 500, message = "An exception was thrown", response = Response.class),
    })
    @GetMapping("/users")
    public List<User> findAll() throws NotFoundException {
        List<User> users = userService.findAll();
        return users;
    }

    public User findByToken(String token) throws NotFoundException {
        Optional<User> user = userService.findByToken(token);
        return user.orElse(null);
    }

    public User findByLoginOrEmail(String loginOrEmail) throws NotFoundException {
        Optional<User> user = userService.findByLoginOrEmail(loginOrEmail);
        return user.orElse(null);
    }

}
