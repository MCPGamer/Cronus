package ch.duartemendes.cronus.service;


import org.springframework.stereotype.Service;

import ch.duartemendes.cronus.domain.List;
import ch.duartemendes.cronus.repository.ListRepository;

@Service
public class ListService {
	private ListRepository listRepository;

	public ListService(ListRepository listRepository) {
		this.listRepository = listRepository;
	}

	public List createList(List list) {
		return listRepository.saveAndFlush(list);
	}

	public java.util.List<List> findAll() {
		return listRepository.findAll();
	}

	public void deleteList(Long id) {
		listRepository.deleteById(id);
	}

	public List updateList(Long id, List list) {
		if (listExistsById(id)) {
			list.setId(id);
			return listRepository.saveAndFlush(list);
		} else {
			throw new RuntimeException("List with id " + id + " not found");
		}
	}

	private boolean listExistsById(Long id) {
		return listRepository.existsById(id);
	}
}
