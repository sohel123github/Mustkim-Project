package com.chatmate.signup.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Getter
public class SignupForm {
    private String username;
    private String name;
    private String password;
}
