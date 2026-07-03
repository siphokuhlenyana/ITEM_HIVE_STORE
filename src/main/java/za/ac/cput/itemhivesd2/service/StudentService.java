package za.ac.cput.service;

import org.springframework.stereotype.Service;
import za.ac.cput.domain.Student;
import za.ac.cput.factory.StudentFactory;
import za.ac.cput.repository.StudentRepository;

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

    public StudentService(StudentRepository student) {
        this.student = student;
    }

    @Override
    public Student create(Student record) {

        Student studentRecord = StudentFactory.createStudent(
                record.getName(),
                record.getEmail(),
                record.getPassword()
        );

        return student.save(studentRecord);
    }

    @Override
    public Student update(Student record) {

        Student studentRecord = StudentFactory.createStudent(
                record.getName(),
                record.getEmail(),
                record.getPassword()
        );

        return student.save(studentRecord);
    }

    @Override
    public Student read(String id) {
        return student.findById(id).orElse(null);
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
