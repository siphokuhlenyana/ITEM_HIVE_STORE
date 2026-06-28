package za.ac.cput.factory;
/*
Author: Mihle Nosilela(240865537)
Date: June 2026
Student Factory
 */

import za.ac.cput.domain.Student;
import za.ac.cput.util.Helper;

import java.util.UUID;

public class StudentFactory {

    public static Student createStudent(String name,
                                        String email,
                                        String password) {

        if (Helper.isNullOrEmpty(name)) {
            throw new IllegalArgumentException("Name is required");
        }

        if (Helper.isNullOrEmpty(email)) {
            throw new IllegalArgumentException("Email is required");
        }

        if (Helper.isNullOrEmpty(password)) {
            throw new IllegalArgumentException("Password is required");
        }

        return new Student.Builder()
                .studentID(generateStudentID())
                .name(name)
                .email(email)
                .password(password)
                .build();
    }

    private static String generateStudentID() {
        return "STU-" + UUID.randomUUID().toString();
    }
}
