package ch.duartemendes.cronus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.duartemendes.cronus.domain.Entry;

public interface EntryRepository extends JpaRepository<Entry, Long> {
}
