package pl.exercise.spring.mvc.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.exercise.spring.mvc.entity.NewspaperEntity;

@Repository
@Transactional
public interface NewspaperRepository extends JpaRepository<NewspaperEntity, Long> {

	List<NewspaperEntity> findAll();
	
}
