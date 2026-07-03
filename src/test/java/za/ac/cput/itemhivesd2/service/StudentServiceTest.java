package za.ac.cput.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import za.ac.cput.domain.Student;
import za.ac.cput.factory.StudentFactory;
import za.ac.cput.repository.StudentRepository;
import za.ac.cput.service.StudentService;

import java.util.List;

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

    @InjectMocks
    private StudentService service;

    @Test
    void testCreate() {

        Student student = mock(Student.class);

        when(student.getName()).thenReturn("Mihle");
        when(student.getEmail()).thenReturn("mihle@gmail.com");
        when(student.getPassword()).thenReturn("12345");

        try (MockedStatic<StudentFactory> factory = mockStatic(StudentFactory.class)) {

            factory.when(() ->
                            StudentFactory.createStudent(
                                    "Mihle",
                                    "mihle@gmail.com",
                                    "12345"))
                    .thenReturn(student);

            when(repository.save(student)).thenReturn(student);

            Student result = service.create(student);

            assertNotNull(result);
            verify(repository).save(student);
        }
    }

    @Test
    void testUpdate() {

        Student student = mock(Student.class);

        when(student.getName()).thenReturn("Mihle");
        when(student.getEmail()).thenReturn("mihle@gmail.com");
        when(student.getPassword()).thenReturn("12345");

        try (MockedStatic<StudentFactory> factory = mockStatic(StudentFactory.class)) {

            factory.when(() ->
                            StudentFactory.createStudent(
                                    "Mihle",
                                    "mihle@gmail.com",
                                    "12345"))
                    .thenReturn(student);

            when(repository.save(student)).thenReturn(student);

            Student result = service.update(student);

            assertNotNull(result);
            verify(repository).save(student);
        }
    }

    @Test
    void testRead() {

        Student student = mock(Student.class);

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
