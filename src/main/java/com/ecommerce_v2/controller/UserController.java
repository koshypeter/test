package com.ecommerce_v2.controller;

import com.ecommerce_v2.Dtos.ResponseDto;
import com.ecommerce_v2.Dtos.user.SignInDto;
import com.ecommerce_v2.Dtos.user.SignInResponseDto;
import com.ecommerce_v2.Dtos.user.SignupDto;
import com.ecommerce_v2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/signup")
    public ResponseDto signup(@RequestBody SignupDto signupDto) {
        return userService.signup(signupDto);
    }
    @PostMapping("/signin")
    public SignInResponseDto signIn(@ResponseBody SignInDto signInDto){
        return userService.signIn(signInDto);
    }
}
