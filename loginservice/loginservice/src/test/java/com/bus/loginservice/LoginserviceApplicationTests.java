package com.bus.loginservice;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bus.loginservice.exception.UserException;
import com.bus.loginservice.model.Login;
import com.bus.loginservice.repository.LoginRepository;
import com.bus.loginservice.service.LoginServiceImpl;

@SpringBootTest
class LoginserviceApplicationTests {

	@InjectMocks
    private LoginServiceImpl service;

    @Mock
    private LoginRepository loginRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        List<Login> Logins = new ArrayList<>();

        when(loginRepository.findAll()).thenReturn(Logins);

        List<Login> result = service.getAllUsers();

        assertNotNull(result);
    }

    @Test
    public void testGetByUsername_UserFound() {
        String username = "testUser";
        Login sampleLogin = new Login();

        when(loginRepository.findByUsername(username)).thenReturn(sampleLogin);

        Login result = service.getByUsername(username);

        assertNotNull(result);
    }

    @Test
    public void testGetByUsername_UserNotFound() {
        String username = "nonExistentUser";

        when(loginRepository.findByUsername(username)).thenReturn(null);

        assertThrows(UserException.class, () -> {
            service.getByUsername(username);
        });
    }

  
	@Test
    public void testUpdateByUsername_UserFound() {
        String username = "testUser";
        Login sampleLogin = new Login();
        String newPassword = "newPassword";

        when(loginRepository.findByUsername(username)).thenReturn(sampleLogin);
        when(passwordEncoder.encode(newPassword)).thenReturn("encodedPassword");
        when(loginRepository.save(sampleLogin)).thenReturn(sampleLogin);

        Login updatedLogin = service.updatePassword(username, sampleLogin);

        assertNotNull(updatedLogin);
    }


}
