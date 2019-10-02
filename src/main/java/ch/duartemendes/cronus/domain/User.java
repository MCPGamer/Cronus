package ch.duartemendes.cronus.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Length;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany
	private java.util.List<Role> roles;

	@OneToOne(mappedBy = "idUser", optional = false, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List list;

	@Length(max = 20)
    @Column(nullable = false)
	private String username;

	@Length(max = 50)
    @Column(nullable = false)
	private String email;

	@Length(max = 32)
    @Column(nullable = false)
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public java.util.List<Role> getRoles() {
		return roles;
	}

	public void setRoles(java.util.List<Role> roles) {
		this.roles = roles;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
