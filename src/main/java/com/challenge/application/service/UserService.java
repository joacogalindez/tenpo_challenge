package com.challenge.application.service;

import com.challenge.application.entity.User;
import com.challenge.application.exception.UserAuthException;
import org.springframework.web.client.HttpClientErrorException;

public interface UserService {

    String validateUser(final String email, final String password) throws UserAuthException, HttpClientErrorException.BadRequest;

    User registerUser(final User user) throws RuntimeException;

    boolean checkIfUserExists(final String email) throws RuntimeException;

    boolean validateJwt(final String authHeader) throws UserAuthException;

}
