package com.chatmate.login.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Getter
public class LoginForm {
    private String username;
    private String password;
}

