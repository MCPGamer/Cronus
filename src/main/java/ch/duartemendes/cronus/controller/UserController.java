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

import ch.duartemendes.cronus.domain.User;
import ch.duartemendes.cronus.dto.UserDTO;
import ch.duartemendes.cronus.mapper.UserMapper;
import ch.duartemendes.cronus.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	/***
	 * Hohlt eine Liste Aller Benutzer, wird in der nächsten Version nur dem Admin
	 * zur verfügung Stehen.
	 */
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public UserDTO[] getAllUsers() {
		return userService.findAll()
				.stream()
				.map(UserMapper.INSTANCE::userToUserDto)
				.toArray(UserDTO[]::new);
	}

	/***
	 * Erstellt einen neuen Benutzer
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserDTO createUser(@Valid @RequestBody User user) {
		return UserMapper.INSTANCE.userToUserDto(userService.createUser(user));
	}

	/***
	 * Löscht einen Benutzer, Dies steht ab der nächsten Version nur dem Eigenen
	 * Benutzer oder dem Admin zur verfügung.
	 */
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable("id") Long id) {
		userService.deleteUser(id);
	}

	/***
	 * Dazu da um Daten wie z.B. ein Passwort eines Benutzers anzupassen
	 */
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public UserDTO putUser(@PathVariable("id") Long id, @Valid @RequestBody User user) {
		return UserMapper.INSTANCE.userToUserDto(userService.updateUser(id, user));
	}
}
