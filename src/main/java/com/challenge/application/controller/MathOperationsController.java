package com.challenge.application.controller;

import com.challenge.application.exception.UserAuthException;
import com.challenge.application.model.Error;
import com.challenge.application.model.MathResponse;
import com.challenge.application.service.HistoryService;
import com.challenge.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/operations")
public class MathOperationsController {

    @Autowired
    private UserService userService;
    @Autowired
    private HistoryService historyService;

    @GetMapping("/multiply/{first}/by/{second}")
    public ResponseEntity<MathResponse> multiplyNumbers(@RequestHeader(value = "Authorization", required = false) String auth,
                                                        @PathVariable String first,
                                                        @PathVariable String second) {
        try {
            historyService.saveHistory("api/operators/multiply/"+first+"/by/"+second);
            boolean isLoggedIn = userService.validateJwt(auth);
            if (isLoggedIn) {
                int first_number = Integer.valueOf(first);
                int second_number = Integer.valueOf(second);

                return new ResponseEntity<>(new MathResponse(first_number * second_number, null), HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (UserAuthException e) {
            return new ResponseEntity<>(new MathResponse(new Error(e.getMessage(), HttpStatus.UNAUTHORIZED.value())), HttpStatus.UNAUTHORIZED);
        } catch(NumberFormatException e) {
            return new ResponseEntity<>(new MathResponse(new Error(e.getMessage(), HttpStatus.BAD_REQUEST.value())), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new MathResponse(new Error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value())), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
