package com.chatmate.signup.controllers;


import com.chatmate.signup.models.SignupForm;
import com.chatmate.signup.models.SignupResponseViewModel;
import com.chatmate.signup.services.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignupController {

    @Autowired
    SignupService signupService;

    @PostMapping("/signup")
    public SignupResponseViewModel signup(
        @RequestBody SignupForm signupForm
    ) {
        return signupService.signup(signupForm);
    }

}
