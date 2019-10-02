package ch.duartemendes.cronus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.duartemendes.cronus.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
