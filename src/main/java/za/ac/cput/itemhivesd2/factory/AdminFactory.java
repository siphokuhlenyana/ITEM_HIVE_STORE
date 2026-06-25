
/* AdminFactory.java
     Author: Siphokuhle Nyana (218123191)

     Date: 25 June 2026
*/ 


package za.ac.cput.itemhivesd2.factory;

import za.ac.cput.itemhivesd2.domain.Admin;
import za.ac.cput.itemhivesd2.util.Helper;

import java.util.UUID;

public class AdminFactory {

    public static Admin createAdmin(String name, String email, String password) {
        if (Helper.isNullOrEmpty(name)) {
            throw new IllegalArgumentException("Name is required");
        }

        if (Helper.isNullOrEmpty(email)) {
            throw new IllegalArgumentException("Email is required");
        }

        if (Helper.isNullOrEmpty(password)) {
            throw new IllegalArgumentException("Password is required");
        }

        return new Admin.Builder()
                .adminID(generateAdminID())
                .name(name)
                .email(email)
                .password(password)
                .build();
    }

    private static String generateAdminID() {
        return "ADMIN-" + UUID.randomUUID().toString();
    }
}
  