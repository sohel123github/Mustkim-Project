package com.chatmate.login.controllers;

import com.chatmate.login.models.LoginForm;
import com.chatmate.login.models.LoginResponseViewModel;
import com.chatmate.login.services.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@RestController
@AllArgsConstructor
public class LoginController {

    private LoginService loginService;

    @RequestMapping("/")
    public String home() {
        return "This is home";
    }

    @PostMapping("/login")
    public LoginResponseViewModel login(HttpServletResponse response, @RequestBody LoginForm loginForm) {
        final LoginResponseViewModel responseViewModel = loginService.login(loginForm);
        if (responseViewModel.isLoginSuccessful()) {
            response.addCookie(new Cookie("username", loginForm.getUsername()));
        }
        return responseViewModel;
    }

}
