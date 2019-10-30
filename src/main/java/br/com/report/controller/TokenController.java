package br.com.report.controller;

import br.com.report.entity.Token;
import br.com.report.service.impl.TokenService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TokenController {

    @Autowired
    private TokenService tokenServiceImpl;

    @PostMapping("/token")
    @ApiOperation(value = "Adds Tokens to the database.")
    public ResponseEntity<Token> addToken(@RequestBody Token token){
       try{
        return new ResponseEntity<>(tokenServiceImpl.addToken(token), HttpStatus.CREATED);
       }catch (Exception e){
           throw e;
       }
    }

    @GetMapping("/token/{id}")
    @ApiOperation(value = "Finds a Token through its id.")
    public ResponseEntity<Token> findById(@PathVariable(value = "id") long id){
       try{
        return new ResponseEntity<>(tokenServiceImpl.findById(id), HttpStatus.OK);
       } catch (Exception e) {
         throw e;
       }
    }

}
