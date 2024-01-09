package net.diogoraposo.todo.repository;

import net.diogoraposo.todo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
