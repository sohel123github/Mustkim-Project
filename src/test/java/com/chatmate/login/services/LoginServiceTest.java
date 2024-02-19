package com.chatmate.login.services;

import com.chatmate.login.models.LoginForm;
import com.chatmate.login.models.LoginResponseViewModel;
import com.chatmate.signup.models.UserEntity;
import com.chatmate.signup.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private LoginService loginService;


    @Test
    public void shouldSetCookieInResponseIfUsernameAndPasswordIsCorrect() {
        final LoginForm loginForm = new LoginForm("username", "password");
        final UserEntity userEntity = new UserEntity("username", "name", "password");

        when(userRepository.findById(loginForm.getUsername())).thenReturn(Optional.of(userEntity));
        final LoginResponseViewModel loginResponseViewModel = loginService.login(loginForm);

        assertThat(loginResponseViewModel.isLoginSuccessful()).isTrue();
        verify(userRepository).findById(loginForm.getUsername());

    }

    @Test
    public void shouldNotSetCookieInResponseIfUsernameOrPasswordIsIncorrect() {
        final LoginForm loginForm = new LoginForm("username", "password");

        when(userRepository.findById(loginForm.getUsername())).thenReturn(Optional.empty());
        final LoginResponseViewModel loginResponseViewModel = loginService.login(loginForm);

        assertThat(loginResponseViewModel.isLoginSuccessful()).isFalse();
        verify(userRepository).findById(loginForm.getUsername());

    }
}