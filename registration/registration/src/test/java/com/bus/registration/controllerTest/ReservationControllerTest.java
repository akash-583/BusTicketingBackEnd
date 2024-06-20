package com.bus.registration.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.bus.registration.controller.RegistrationController;
import com.bus.registration.model.Registration;
import com.bus.registration.service.RegistrationServiceImpl;

@SpringBootTest
public class ReservationControllerTest {

	@Mock
    private RegistrationServiceImpl registrationService;

    @InjectMocks
    private RegistrationController registrationController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
  public void testSaveUserWithValidRole() throws Exception {
      // Arrange
      Registration newUser = new Registration();
      newUser.setUsername("newuser");
      newUser.setPassword("password");
      newUser.setRole("User");

      when(registrationService.save(newUser)).thenReturn(newUser);

      // Act
      ResponseEntity<?> responseEntity = registrationController.saveUser(newUser);

      // Assert
      assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
      assertEquals(newUser, responseEntity.getBody());
  }

    @Test
    public void testSaveUserWithInvalidRole() throws Exception {
        Registration newUser = new Registration();
        newUser.setUsername("newuser");
        newUser.setPassword("password");
        newUser.setRole("InvalidRole");

        ResponseEntity<?> responseEntity = registrationController.saveUser(newUser);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Please Select a valid role", responseEntity.getBody());
    }

}
