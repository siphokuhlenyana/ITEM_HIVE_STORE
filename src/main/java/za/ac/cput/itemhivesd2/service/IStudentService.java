package za.ac.cput.itemhivesd2.service;

import za.ac.cput.itemhivesd2.domain.Student;

import java.util.List;

public interface IStudentService {

    Student create(Student record);

    Student update(Student record);

    Student read(String id);

    List<Student> getAll();

    void delete(String id);
}
