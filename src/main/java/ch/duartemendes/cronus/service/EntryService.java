package ch.duartemendes.cronus.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ch.duartemendes.cronus.domain.Entry;
import ch.duartemendes.cronus.repository.EntryRepository;

@Service
public class EntryService {
	private EntryRepository entryRepository;

	public EntryService(EntryRepository entryRepository) {
		this.entryRepository = entryRepository;
	}

	public Entry createEntry(Entry entry) {
		return entryRepository.saveAndFlush(entry);
	}

	public List<Entry> findAll() {
		return entryRepository.findAll();
	}

	public void deleteEntry(Long id) {
		entryRepository.deleteById(id);
	}

	public Entry updateEntry(Long id, Entry entry) {
		if (entryExistsById(id)) {
			entry.setId(id);
			return entryRepository.saveAndFlush(entry);
		} else {
			throw new RuntimeException("Entry with id " + id + " not found");
		}
	}

	private boolean entryExistsById(Long id) {
		return entryRepository.existsById(id);
	}
}
