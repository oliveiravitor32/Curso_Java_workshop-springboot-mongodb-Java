package com.oliveiravitor.workshopmongo.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.oliveiravitor.workshopmongo.domain.Post;
import com.oliveiravitor.workshopmongo.domain.User;
import com.oliveiravitor.workshopmongo.dto.AuthorDTO;
import com.oliveiravitor.workshopmongo.repository.PostRepository;
import com.oliveiravitor.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		AuthorDTO authorMaria = new AuthorDTO(maria);
		
		Post post1 = new Post(null, Instant.now(), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", authorMaria);

		Post post2 = new Post(null,  Instant.parse("2023-06-20T19:53:07Z"), "Bom dia!", "Acordei feliz hoje!", authorMaria);

		
		postRepository.saveAll(Arrays.asList(post1, post2));
	}

}
