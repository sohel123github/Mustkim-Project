package com.chatmate.login.controllers;

import com.chatmate.login.models.LoginForm;
import com.chatmate.login.models.LoginResponseViewModel;
import com.chatmate.login.services.LoginService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Mock
    private LoginService loginService;

    @InjectMocks
    private LoginController loginController;

    @Before
    public void setUp() throws Exception {
        mockMvc = standaloneSetup(loginController).build();

    }


    @Test
    public void shouldReturnTrueIfUserSignupIsSucessful() throws Exception {
        final LoginForm loginForm = new LoginForm("username", "password");
        when(loginService.login(loginForm)).thenReturn(new LoginResponseViewModel(true, null));


        mockMvc.perform(MockMvcRequestBuilders.post("/login")
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content("{\"username\": \"username\", \"password\": \"password\"}")
            .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.loginSuccessful").value(true))
            .andExpect(MockMvcResultMatchers.jsonPath("$.error").doesNotExist())
            .andExpect(cookie().value("username", "username"))
            .andReturn();
    }

    @Test
    public void shouldReturnFalseIfUserSignupIsNotSucessful() throws Exception {
        final LoginForm loginForm = new LoginForm("username", "password");
        when(loginService.login(loginForm)).thenReturn(new LoginResponseViewModel(false, "Wrong Password"));

        mockMvc.perform(MockMvcRequestBuilders.post("/login")
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content("{\"username\": \"username\", \"password\": \"password\"}")
            .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.loginSuccessful").value(false))
            .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Wrong Password"))
            .andReturn();
    }
}