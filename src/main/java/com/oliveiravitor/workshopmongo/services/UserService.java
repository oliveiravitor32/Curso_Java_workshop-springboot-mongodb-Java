package com.oliveiravitor.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oliveiravitor.workshopmongo.domain.User;
import com.oliveiravitor.workshopmongo.dto.UserDTO;
import com.oliveiravitor.workshopmongo.repository.UserRepository;
import com.oliveiravitor.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;

	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(String id) {
		Optional<User> user = repository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Object not found. Id: " + id));
	}
	
	public User insert(User user) {
		return repository.insert(user);
	}
	
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public User update(User user) {
		Optional<User> optionalUser = repository.findById(user.getId());
		User newUser = optionalUser.orElseThrow(() -> new ObjectNotFoundException("Object not found. Id: " + user.getId()));
		updateDate(newUser, user);
		return repository.save(newUser);
	}
	
	private void updateDate(User newUser, User user) {
		newUser.setName(user.getName());
		newUser.setEmail(user.getEmail());
	}
	
	public User fromDTO(UserDTO userDto) {
		return new User(userDto.getId(), userDto.getName(), userDto.getEmail()); 
	}
}
