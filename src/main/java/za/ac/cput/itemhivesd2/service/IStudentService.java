package za.ac.cput.service;

import za.ac.cput.domain.Student;

import java.util.List;

public interface IStudentService {

    Student create(Student record);

    Student update(Student record);

    Student read(String id);

    List<Student> getAll();

    void delete(String id);
}
