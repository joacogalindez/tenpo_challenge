package com.challenge.application.service;

import com.challenge.application.constants.Constants;
import com.challenge.application.entity.User;
import com.challenge.application.exception.BadRequestException;
import com.challenge.application.exception.UserAuthException;
import com.challenge.application.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    HistoryService historySerivce;


    @Override
    public String validateUser(String email, String password) throws UserAuthException, HttpClientErrorException.BadRequest {

        if (email == null || password == null) {
            throw new BadRequestException("Email or Password are empty");
        }
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserAuthException("Email is not registered");
        }
        if (user.getPassword().equals(password)) {
            return generateJWT(email);
        } else {
            throw new UserAuthException("Email or password does not match");
        }
    }

    @Override
    public User registerUser(final User user) throws RuntimeException {
        boolean matches = false;
        if (user.isInvalidUser()) {
            throw new BadRequestException("Invalid Email or Password for registration.");
        }
        try {
            Pattern pattern = Pattern.compile("^(.+)@(.+)$");
            Matcher m = pattern.matcher(user.getEmail());
            matches = m.matches();
        } catch (PatternSyntaxException e) {
            throw new BadRequestException("Invalid Email for registration.");
        }
        if (!matches) {
            throw new BadRequestException("Invalid Email for registration.");
        }
        if (checkIfUserExists(user.getEmail())) {
            throw new BadRequestException("A User already exists with the submitted email. Please use another.");
        } else {
            return userRepository.save(user);
        }
    }


    @Override
    public boolean checkIfUserExists(String email) throws RuntimeException {
        return userRepository.findByEmail(email) != null ? true : false;
    }

    private String generateJWT(final String email) {
        long timeStamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.SECRET_KEY)
                .setIssuedAt(new Date(timeStamp))
                .setExpiration(new Date(timeStamp + Constants.TOKEN_VALIDITY))
                .claim("email", email)
                .compact();

        return token;
    }

    public boolean validateJwt(final String authHeader) {
        if (authHeader != null) {

            String[] authHeaderArr = authHeader.split("Bearer ");
            if (authHeaderArr.length > 1 && authHeaderArr[1] != null) {
                try {
                    String token = authHeaderArr[1];
                    Claims claims = Jwts.parser().setSigningKey(Constants.SECRET_KEY).parseClaimsJws(token).getBody();
                    return true;
                } catch (Exception e) {
                    throw new UserAuthException("Invalid Authorization Header");
                }
            } else {
                throw new UserAuthException("Invalid Authorization Header. Desired format is 'Bearer token'");
            }
        } else {
            throw new UserAuthException("Authorization Header is missing");
        }
    }

    ;

}
