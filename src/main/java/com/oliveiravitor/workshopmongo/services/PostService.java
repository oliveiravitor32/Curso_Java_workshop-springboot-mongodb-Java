package com.oliveiravitor.workshopmongo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oliveiravitor.workshopmongo.domain.Post;
import com.oliveiravitor.workshopmongo.repository.PostRepository;
import com.oliveiravitor.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repository;

	public Post findById(String id) {
		Optional<Post> post = repository.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException("Object not found. Id: " + id));
	}
}
