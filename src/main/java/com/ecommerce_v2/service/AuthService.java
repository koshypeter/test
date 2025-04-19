package com.ecommerce_v2.service;

import com.ecommerce_v2.exceptions.AuthenticationFailException;
import com.ecommerce_v2.model.AuthenticationToken;
import com.ecommerce_v2.model.User;
import com.ecommerce_v2.repositories.TokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService {

    @Autowired
    TokenRepo tokenRepo;
    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        tokenRepo.save(authenticationToken);
    }

    public AuthenticationToken getToken(User user) {
        return tokenRepo.findByUser(user);
    }

    public User getUser(String token){
        final AuthenticationToken token1=tokenRepo.findByToken(token);
        if(Objects.isNull(token1)){
            return null;
        }
        else{
            return token1.getUser();
        }
    }
    public void authenticate(String token){
        //checking whether the token exists
        if(Objects.isNull(token)){
            throw new AuthenticationFailException("token does not exist");
        }
        if(Objects.isNull(getUser(token))){
            throw new AuthenticationFailException("token is not valid");
        }
    }
}
