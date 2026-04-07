package com.EmailVerification.Controller;

import com.EmailVerification.Model.TokenModel;
import com.EmailVerification.Model.UserModel;
import com.EmailVerification.Repo.TokenRepo;
import com.EmailVerification.Repo.UserRepo;
import com.EmailVerification.Service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TokenRepo tokenRepo;

    @Autowired
    private MailService service;

    @PostMapping("/register")
    public String register(@RequestBody UserModel user) throws Exception {

        user.setVerified(false);

        userRepo.save(user);

        String token = UUID.randomUUID().toString();
         TokenModel tokenModel = new TokenModel();

         tokenModel.setToken(token);
         tokenModel.setUser(user);

         tokenRepo.save(tokenModel);

         service.sendEmail(user.getEmail(), token);

            return "Verification email sent";
    }

    @GetMapping("/verify")

    public String verifyEmail(@RequestParam String token){

        TokenModel tokenModel = tokenRepo.findByToken(token).orElseThrow(()->new RuntimeException("Invalid Token"));

        UserModel userModel = tokenModel.getUser();
        userModel.setVerified(true);
        userRepo.save(userModel);

        return "Email verified successfully";
    }
}
