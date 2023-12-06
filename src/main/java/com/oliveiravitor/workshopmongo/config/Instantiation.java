package com.oliveiravitor.workshopmongo.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.oliveiravitor.workshopmongo.domain.Post;
import com.oliveiravitor.workshopmongo.domain.User;
import com.oliveiravitor.workshopmongo.dto.AuthorDTO;
import com.oliveiravitor.workshopmongo.dto.CommentDTO;
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
		AuthorDTO authorAlex = new AuthorDTO(alex);
		AuthorDTO authorBob = new AuthorDTO(bob);
		
		Post post1 = new Post(null, Instant.now(), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", authorMaria);

		Post post2 = new Post(null,  Instant.parse("2023-06-20T19:53:07Z"), "Bom dia!", "Acordei feliz hoje!", authorMaria);

		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
		
		
		CommentDTO comm1 = new CommentDTO("Boa viagem!", Instant.now(), authorAlex);
		CommentDTO comm2 = new CommentDTO("Aproveite!", Instant.now(), authorBob);
		CommentDTO comm3 = new CommentDTO("Tenha um ótimo dia!", Instant.now(), authorAlex);
		
		post1.getComments().addAll(Arrays.asList(comm1, comm2));
		post2.getComments().add(comm3);
		
		postRepository.saveAll(Arrays.asList(post1, post2));
	}
}
