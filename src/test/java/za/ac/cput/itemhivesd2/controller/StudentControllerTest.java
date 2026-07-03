package za.ac.cput.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import za.ac.cput.service.StudentService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/*
StudentControllerTest.java
Student controller test class

Author: Mihle Nosilela (240865537)
Date: June 2026
*/

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {

    @Mock
    private StudentService service;

    @InjectMocks
    private StudentController controller;

    @Test
    void testGetAll() {

        when(service.getAll()).thenReturn(List.of());

        List<?> result = controller.getAll();

        assertNotNull(result);
        verify(service).getAll();
    }
}