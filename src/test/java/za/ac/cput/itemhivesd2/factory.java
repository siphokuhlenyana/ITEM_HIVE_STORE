package za.ac.cput;
/* StudentFactoryTest.java
   Test class for StudentFactory

   Author: Mihle Nosilela (240865537)
   Date: 2026
*/

import org.junit.jupiter.api.Test;
import za.ac.cput.factory.StudentFactory;
import za.ac.cput.domain.Student;

import static org.junit.jupiter.api.Assertions.*;

class StudentFactoryTest {

    @Test
    void createStudent() {
        Student student = StudentFactory.createStudent(
                "S001",
                "Mihle",
                "mihle@email.com",
                "1234"
        );

        assertNotNull(student);
        assertEquals("S001", student.getStudentID());
        assertEquals("Mihle", student.getName());
    }

    @Test
    void createStudentNull() {
        Student student = StudentFactory.createStudent(
                null,
                "Mihle",
                "mihle@email.com",
                "1234"
        );

        assertNull(student);
    }
}