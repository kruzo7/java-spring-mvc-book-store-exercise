package pl.exercise.spring.mvc.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.exercise.spring.mvc.entity.AuthorEntity;

@Repository
@Transactional
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

	List<AuthorEntity> findAll();
	
	Optional<AuthorEntity> findByFirstNameAndLastName(String firstName, String lastName);

}
