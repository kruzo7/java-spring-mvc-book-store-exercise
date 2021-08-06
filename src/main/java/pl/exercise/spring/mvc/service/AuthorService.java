package pl.exercise.spring.mvc.service;

import java.util.List;
import java.util.Optional;

import pl.exercise.spring.mvc.entity.AuthorEntity;

public interface AuthorService {

	List<AuthorEntity> findAll();

	AuthorEntity findOne(Long id);
	
	Boolean exist(Long id);
	
	AuthorEntity save(AuthorEntity authorEntity);
	
	Optional<AuthorEntity> find(String firstName, String lastName);

}
