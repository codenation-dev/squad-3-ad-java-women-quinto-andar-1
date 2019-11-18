package br.com.report.controller;

import br.com.report.dto.ResponseDTO;
import br.com.report.entity.User;
import br.com.report.payload.Response;
import br.com.report.payload.JwtAuthenticationResponse;
import br.com.report.payload.LoginRequest;
import br.com.report.payload.SignUpRequest;
import br.com.report.repository.UserRepository;
import br.com.report.security.JwtTokenProvider;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @ApiOperation(value = "Authenticate user access in api")
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        System.out.println(loginRequest.getLogin() + " " + loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getLogin(),
                        loginRequest.getPassword()
                )
        );

        Optional<User> login = userRepository.findByLoginOrEmail(loginRequest.getLogin(), loginRequest.getLogin());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        //new JwtAuthenticationResponse(jwt)
        ResponseDTO response = new ResponseDTO();
        response.jwtAuthenticationResponse = new JwtAuthenticationResponse(jwt);
        response.user = login.get();
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Add a new user in database")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Retorna a lista de pessoa", response = Response.class),
            @ApiResponse(code = 401, message = "Você não tem permissão para acessar este recurso", response = Response.class),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção", response = Response.class),
    })
    @PostMapping("/cad")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByLogin(signUpRequest.getLogin())) {
            return new ResponseEntity(new Response(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new Response(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getLogin(), signUpRequest.getEmail(), signUpRequest.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setToken();
        User result = userRepository.save(user);

        return new ResponseEntity(new Response(true, "User registered successfully"),
                HttpStatus.CREATED);
 }
}