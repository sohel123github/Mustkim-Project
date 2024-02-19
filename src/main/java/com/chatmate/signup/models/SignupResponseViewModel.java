package com.chatmate.signup.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SignupResponseViewModel {
    private boolean signupSuccessful;
    private String error;
}
