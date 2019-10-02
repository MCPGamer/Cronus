package ch.duartemendes.cronus.controller;

import java.util.List;

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
import ch.duartemendes.cronus.service.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Role> getAllRoles() {
        return roleService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Role createRole(@Valid @RequestBody Role role) {
        return roleService.createRole(role);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRole(@PathVariable("id") Long id) {
        roleService.deleteRole(id);
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Role putRole(@PathVariable("id") Long id, @Valid @RequestBody Role role) {
        return roleService.updateRole(id, role);
    }
}
