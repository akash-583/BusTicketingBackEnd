package com.bus.registration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bus.registration.exception.RegistrationException;
import com.bus.registration.model.Registration;
import com.bus.registration.repository.RegistrationRepository;
import com.bus.registration.service.RegistrationServiceImpl;

@SpringBootTest
class RegistrationApplicationTests {

	@Mock
    private RegistrationRepository registrationRepository;

    @Mock
    private PasswordEncoder bcryptEncoder;

    @InjectMocks
    private RegistrationServiceImpl registrationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveNewUser() {
        Registration newUser = new Registration("newuser", "password", "USER", "Male", "newuser@example.com", 25, "Country");

        when(registrationRepository.findByUsername("newuser")).thenReturn(null);
        when(bcryptEncoder.encode("password")).thenReturn("encodedPassword");
        when(registrationRepository.save(any(Registration.class))).thenReturn(newUser); 

        Registration savedUser = registrationService.save(newUser);
        assertEquals("newuser", savedUser.getUsername());
        assertEquals("USER", savedUser.getRole());
    }


    @Test
    public void testSaveUserWithExistingUsername() {
        Registration existingUser = new Registration();
        existingUser.setUsername("existinguser");

        Registration newUser = new Registration();
        newUser.setUsername("existinguser");

        when(registrationRepository.findByUsername("existinguser")).thenReturn(existingUser);

        assertThrows(RegistrationException.class, () -> registrationService.save(newUser));

    }

}
