package ch.duartemendes.cronus.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class List {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "idList", cascade = CascadeType.REMOVE)
	@JsonIgnore
    private java.util.List<Entry> entries;

    @OneToOne(optional = false, cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JsonIgnore
	private User idUser;
    
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public java.util.List<Entry> getEntries() {
		return entries;
	}

	public void setEntries(java.util.List<Entry> entries) {
		this.entries = entries;
	}

	public User getIdUser() {
		return idUser;
	}

	public void setIdUser(User idUser) {
		this.idUser = idUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}