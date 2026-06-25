package za.ac.cput.factory;
/*
Author: Mihle Nosilela(240865537)
Date: June 2026
Student Factory
 */

import za.ac.cput.domain.Student;

public class StudentFactory {

    public static Student createStudent(String studentID,
                                        String name,
                                        String email,
                                        String password) {

        if (studentID == null || name == null || email == null || password == null) {
            return null;
        }

        return new Student.Builder()
                .studentID(studentID)
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
}

