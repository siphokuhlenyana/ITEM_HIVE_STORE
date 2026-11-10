package za.ac.cput.itemhivesd2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.itemhivesd2.domain.Student;

public interface StudentRepository extends JpaRepository<Student, String> {
    boolean existsByEmailIgnoreCase(String email);
}