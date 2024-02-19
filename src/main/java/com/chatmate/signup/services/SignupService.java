package com.chatmate.signup.services;

import com.chatmate.signup.models.SignupForm;
import com.chatmate.signup.models.SignupResponseViewModel;
import com.chatmate.signup.models.UserEntity;
import com.chatmate.signup.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.xml.ws.http.HTTPException;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SignupService {

    private UserRepository userRepository;

    public SignupResponseViewModel signup(SignupForm signupForm) {
        final UserEntity user = mapToUserEntity(signupForm);
        final Optional<UserEntity> optionalUserEntity = userRepository.findById(user.getUsername());
        if (optionalUserEntity.isPresent()) {
            return new SignupResponseViewModel(false,"Username already exists");
        }
        userRepository.save(user);
        return new SignupResponseViewModel(true,null);
    }

    private UserEntity mapToUserEntity(SignupForm signupForm) throws HTTPException {
        return new UserEntity(signupForm.getUsername(), signupForm.getName(), signupForm.getPassword());
    }
}
