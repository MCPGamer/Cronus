package ch.duartemendes.cronus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.duartemendes.cronus.domain.List;

public interface ListRepository extends JpaRepository<List, Long> {
}
