package com.ecommerce_v2.service;

import com.ecommerce_v2.Dtos.ResponseDto;
import com.ecommerce_v2.Dtos.user.SignInDto;
import com.ecommerce_v2.Dtos.user.SignInResponseDto;
import com.ecommerce_v2.Dtos.user.SignupDto;
import com.ecommerce_v2.exceptions.AuthenticationFailException;
import com.ecommerce_v2.exceptions.CustomException;
import com.ecommerce_v2.model.AuthenticationToken;
import com.ecommerce_v2.model.User;
import com.ecommerce_v2.repositories.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    AuthService authService;
    @Transactional
    public ResponseDto signup(SignupDto signupDto) {
       //check if user already present
        if (Objects.nonNull(userRepo.findByEmail(signupDto.getEmail()))){
            throw new CustomException("user already exists");
        }
        //hash the password
        String encryptedpassword=signupDto.getPassword();

        try{
            encryptedpassword=hashPassword(signupDto.getPassword());
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }

        User user=new User();
        user.setEmail(signupDto.getEmail());
        user.setFname(signupDto.getFname());
        user.setLname(signupDto.getLname());
        user.setPassword(encryptedpassword);
        userRepo.save(user);

        AuthenticationToken authenticationToken = new AuthenticationToken(user);
        authService.saveConfirmationToken(authenticationToken);

        ResponseDto responseDto=new ResponseDto("success","Account created");
        return responseDto;
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md=MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest=md.digest();
        String hash = HexFormat.of().formatHex(digest).toUpperCase();
        return hash;
    }

    public SignInResponseDto signIn(SignInDto signInDto) {
        User user = userRepo.findByEmail(signInDto.getEmail());
        if (Objects.isNull(user)) {
            throw new AuthenticationFailException("user not found");
        }
        try{
            if (!user.getPassword().equals(hashPassword(signInDto.getPassword()))){
                throw new AuthenticationFailException("Email or password is incorrect");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        AuthenticationToken token = authService.getToken(user);

        if(Objects.isNull(token)){
            throw new CustomException("token is not present");
        }

        return new SignInResponseDto("success",token.getToken());
    }
}
