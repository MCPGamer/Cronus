package ch.duartemendes.cronus.dto;

import java.util.List;

public class ListDTO {
	private Long id;
	private List<Long> entries;
	private Long idUser;
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Long> getEntries() {
		return entries;
	}

	public void setEntries(List<Long> entries) {
		this.entries = entries;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
