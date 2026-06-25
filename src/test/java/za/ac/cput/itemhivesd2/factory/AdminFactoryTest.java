
/* AdminFactoryTest.java
     Author: Siphokuhle Nyana (218123191)

     Date: 25 June 2026
*/ 


package za.ac.cput.itemhivesd2.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.itemhivesd2.domain.Admin;

import static org.junit.jupiter.api.Assertions.*;

public class AdminFactoryTest {

    @Test
    void testCreateAdmin() {
        Admin admin = AdminFactory.createAdmin(
                "Siphokuhle",
                "Snyana@gmail.com",
                "123testing"
        );

        assertNotNull(admin);
        assertEquals("Siphokuhle", admin.getName());
        assertEquals("Snyana@gmail.com", admin.getEmail());
        assertEquals("123testing", admin.getPassword());
        assertNotNull(admin.getAdminID()); // generated automatically
    }
}
 