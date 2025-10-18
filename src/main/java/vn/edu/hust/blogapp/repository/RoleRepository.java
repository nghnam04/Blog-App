package vn.edu.hust.blogapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hust.blogapp.constant.RoleEnum;
import vn.edu.hust.blogapp.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleEnum name);
}
