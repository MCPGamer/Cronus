package ch.duartemendes.cronus.dto;

import java.time.LocalDateTime;

public class EntryDTO {
	private Long id;
	private Long idList;
	private String entryText;
	private LocalDateTime dueDate;
	private Boolean done;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdList() {
		return idList;
	}

	public void setIdList(Long idList) {
		this.idList = idList;
	}

	public String getEntryText() {
		return entryText;
	}

	public void setEntryText(String entryText) {
		this.entryText = entryText;
	}

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}
}
