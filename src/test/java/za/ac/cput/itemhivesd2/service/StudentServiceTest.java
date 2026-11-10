package za.ac.cput.itemhivesd2.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import za.ac.cput.itemhivesd2.domain.Student;
import za.ac.cput.itemhivesd2.repository.StudentRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/*
StudentServiceTest.java
Student service test class

Author: Mihle Nosilela (240865537)
Date: June 2026
*/

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private StudentService service;

    @Test
    void testCreate() {

        Student student = new Student();
        student.setName("Mihle");
        student.setEmail("mihle@gmail.com");
        student.setPassword("12345");
        when(repository.existsByEmailIgnoreCase(student.getEmail())).thenReturn(false);
        when(passwordEncoder.encode("12345")).thenReturn("hashed-password");
        when(repository.save(any(Student.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Student result = service.create(student);

        assertNotNull(result);
        assertEquals("hashed-password", result.getPassword());
        verify(repository).save(any(Student.class));
    }

    @Test
    void testUpdate() {

        Student student = new Student();
        student.setId("STU001");
        student.setName("Mihle Updated");
        student.setEmail("mihle@gmail.com");
        student.setPassword("new-password");
        Student existing = new Student();
        existing.setId("STU001");
        when(repository.findById("STU001")).thenReturn(java.util.Optional.of(existing));
        when(passwordEncoder.encode("new-password")).thenReturn("hashed-password");
        when(repository.save(existing)).thenReturn(existing);

        Student result = service.update(student);

        assertNotNull(result);
        assertEquals("hashed-password", result.getPassword());
        verify(repository).save(existing);
    }

    @Test
    void testRead() {

        Student student = new Student();

        when(repository.findById("STU001"))
                .thenReturn(java.util.Optional.of(student));

        Student result = service.read("STU001");

        assertNotNull(result);
        verify(repository).findById("STU001");
    }

    @Test
    void testGetAll() {

        when(repository.findAll()).thenReturn(List.of());

        List<Student> result = service.getAll();

        assertNotNull(result);
        verify(repository).findAll();
    }

    @Test
    void testDelete() {

        service.delete("STU001");

        verify(repository).deleteById("STU001");
    }
}
