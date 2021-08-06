package pl.exercise.spring.mvc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.exercise.spring.mvc.entity.AuthorEntity;
import pl.exercise.spring.mvc.repository.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	public List<AuthorEntity> findAll() {
		return this.authorRepository.findAll();
	}

	public AuthorEntity findOne(Long id) {
		return this.authorRepository.findOne(id);
	}

	public Boolean exist(Long id) {
		return this.authorRepository.exists(id);
	}
	
	public AuthorEntity save(AuthorEntity authorEntity) {
		return this.authorRepository.save(authorEntity);
	}
	
	public Optional<AuthorEntity> find(String firstName, String lastName) {
		
		//this.authorRepository.
		return this.authorRepository.findByFirstNameAndLastName(firstName, lastName);
	}

}
