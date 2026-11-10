package za.ac.cput.itemhivesd2.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import za.ac.cput.itemhivesd2.domain.Student;
import za.ac.cput.itemhivesd2.factory.StudentFactory;
import za.ac.cput.itemhivesd2.repository.StudentRepository;

import java.util.List;

/*
StudentService.java
Student service class

Author: Mihle Nosilela (240865537)
Date: June 2026
*/

@Service
public class StudentService implements IStudentService {

    private final StudentRepository student;
    private final PasswordEncoder passwordEncoder;

    public StudentService(StudentRepository student, PasswordEncoder passwordEncoder) {
        this.student = student;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Student create(Student record) {

        if (student.existsByEmailIgnoreCase(record.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email is already registered");
        }
        Student studentRecord = StudentFactory.createStudent(record.getName(), record.getEmail(), record.getPassword());
        studentRecord.setPassword(passwordEncoder.encode(studentRecord.getPassword()));

        return student.save(studentRecord);
    }

    @Override
    public Student update(Student record) {

        Student existing = read(record.getId());
        existing.setName(record.getName());
        existing.setEmail(record.getEmail());
        if (record.getPassword() != null && !record.getPassword().isBlank()) {
            existing.setPassword(passwordEncoder.encode(record.getPassword()));
        }
        return student.save(existing);
    }

    @Override
    public Student read(String id) {
        return student.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
    }

    @Override
    public List<Student> getAll() {
        return student.findAll();
    }

    @Override
    public void delete(String id) {
        student.deleteById(id);
    }
}
