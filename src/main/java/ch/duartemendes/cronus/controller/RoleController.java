package ch.duartemendes.cronus.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ch.duartemendes.cronus.domain.Role;
import ch.duartemendes.cronus.dto.RoleDTO;
import ch.duartemendes.cronus.mapper.RoleMapper;
import ch.duartemendes.cronus.service.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /***
     * Zeigt alle Rollen an. Ab Nächster Version nur für Admin.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public RoleDTO[] getAllRoles() {
        return roleService.findAll()
				.stream()
				.map(RoleMapper.INSTANCE::roleToRoleDto)
				.toArray(RoleDTO[]::new);
    }

    /***
     * Neue Rolle Estellen. Ab nächster Version nur für Admin.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoleDTO createRole(@Valid @RequestBody Role role) {
        return RoleMapper.INSTANCE.roleToRoleDto(roleService.createRole(role));
    }
    
    /***
     * Löscht eine Rolle. Ab Nächster Version nur für Admin.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRole(@PathVariable("id") Long id) {
        roleService.deleteRole(id);
    }
    
    /***
     * Name oder sonstige Daten einer Rolle anpassen. Ab Nächster Version nur für Admin.
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoleDTO putRole(@PathVariable("id") Long id, @Valid @RequestBody Role role) {
        return RoleMapper.INSTANCE.roleToRoleDto(roleService.updateRole(id, role));
    }
}
