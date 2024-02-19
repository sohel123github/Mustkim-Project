package com.chatmate.login.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class LoginResponseViewModel {
    private boolean loginSuccessful;
    private String error;
}
