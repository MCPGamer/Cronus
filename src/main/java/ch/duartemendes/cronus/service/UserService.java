package ch.duartemendes.cronus.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;

import ch.duartemendes.cronus.domain.User;
import ch.duartemendes.cronus.repository.UserRepository;

@Service
public class UserService {
    @PersistenceContext 
    private EntityManager entityManager;
	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User createUser(User user) {
		return userRepository.saveAndFlush(user);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	public User updateUser(Long id, User user) {
		if (userExistsById(id)) {
			user.setId(id);
			return userRepository.saveAndFlush(user);
		} else {
			throw new RuntimeException("User with id " + id + " not found");
		}
	}

	private boolean userExistsById(Long id) {
		return userRepository.existsById(id);
	}

	public User findByLoginName(String loginName) {
		String queryString = "SELECT u FROM User as u "
				+ "WHERE u.username LIKE :loginName OR u.email LIKE :loginName";

		TypedQuery<User> query = entityManager.createQuery(queryString, User.class);
		query.setParameter("loginName", loginName);
		return (User) query.getSingleResult();
	}
}
