package ch.duartemendes.cronus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.duartemendes.cronus.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
