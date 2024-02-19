package com.chatmate.login.services;

import com.chatmate.login.models.LoginForm;
import com.chatmate.login.models.LoginResponseViewModel;
import com.chatmate.signup.models.UserEntity;
import com.chatmate.signup.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginService {

    private UserRepository userRepository;

    public LoginResponseViewModel login(LoginForm loginForm) {
        final Optional<UserEntity> optionalUserEntity = userRepository.findById(loginForm.getUsername());
        if (!optionalUserEntity.isPresent()) {
            return new LoginResponseViewModel(false, "No account exists with this username.");
        }
        final UserEntity userEntity = optionalUserEntity.get();
        if (userEntity.getPassword().equals(loginForm.getPassword())) {
            return new LoginResponseViewModel(true, null);
        }
        return new LoginResponseViewModel(true, "Wrong Password");
    }
}
