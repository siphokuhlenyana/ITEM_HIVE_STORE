package za.ac.cput.itemhivesd2.factory;

import za.ac.cput.itemhivesd2.domain.Student;

public final class StudentFactory {

    private StudentFactory() {
    }

    public static Student createStudent(String name, String email, String password) {
        Student student = new Student();
        student.setName(name);
        student.setEmail(email == null ? null : email.trim().toLowerCase());
        student.setPassword(password);
        return student;
    }
}