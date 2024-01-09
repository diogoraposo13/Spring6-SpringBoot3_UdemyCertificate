package net.diogoraposo.sms.repository;

import net.diogoraposo.sms.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
