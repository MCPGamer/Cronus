package ch.duartemendes.cronus.service;


import java.util.List;

import org.springframework.stereotype.Service;

import ch.duartemendes.cronus.domain.Role;
import ch.duartemendes.cronus.repository.RoleRepository;

@Service
public class RoleService {
	private RoleRepository roleRepository;

	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public Role createRole(Role role) {
		return roleRepository.saveAndFlush(role);
	}

	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	public void deleteRole(Long id) {
		roleRepository.deleteById(id);
	}

	public Role updateRole(Long id, Role role) {
		if (roleExistsById(id)) {
			role.setId(id);
			return roleRepository.saveAndFlush(role);
		} else {
			throw new RuntimeException("Role with id " + id + " not found");
		}
	}

	private boolean roleExistsById(Long id) {
		return roleRepository.existsById(id);
	}
}
