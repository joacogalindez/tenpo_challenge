package com.challenge.application.controller;

import com.challenge.application.entity.User;
import com.challenge.application.exception.BadRequestException;
import com.challenge.application.exception.UserAuthException;
import com.challenge.application.model.Error;
import com.challenge.application.model.LoginRequest;
import com.challenge.application.model.UserResponse;
import com.challenge.application.service.HistoryService;
import com.challenge.application.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private HistoryService historyService;

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> createUser(@RequestBody User user) {
        try {
            User response = userService.registerUser(user);
            historyService.saveHistory("api/users/register");
            return new ResponseEntity<>(new UserResponse(user, null, null), HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(new UserResponse(new Error(e.getMessage(), HttpStatus.BAD_REQUEST.value())), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new UserResponse(new Error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value())), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="User login", notes = "All fields are mandatory")
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest user) {
        try {
            String token = userService.validateUser(user.getEmail(), user.getPassword());
            historyService.saveHistory("api/users/login");
            return new ResponseEntity<>(new UserResponse(null, token, null), HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(new UserResponse(new Error(e.getMessage(), HttpStatus.BAD_REQUEST.value())), HttpStatus.BAD_REQUEST);
        } catch (UserAuthException e) {
            return new ResponseEntity<>(new UserResponse(new Error(e.getMessage(), HttpStatus.UNAUTHORIZED.value())), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(new UserResponse(new Error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value())), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    ;

    @RequestMapping(value = "/logout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="User login", notes = "Dummy Logout, Session expires via JWT")
    public ResponseEntity<String> logout(@RequestBody LoginRequest user) {
        try {
            historyService.saveHistory("api/users/logout");
            //Se Podría implementar con redis una blacklist de JWT para finalizar la sesion antes de su expiración.
            return new ResponseEntity<>("User's Session will expire whit the JWT.", HttpStatus.OK);
        } catch (UserAuthException e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    ;
}
