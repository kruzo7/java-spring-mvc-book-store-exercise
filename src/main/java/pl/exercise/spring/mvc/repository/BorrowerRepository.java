package pl.exercise.spring.mvc.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.exercise.spring.mvc.entity.BorrowerEntity;

@Repository
@Transactional
public interface BorrowerRepository extends JpaRepository<BorrowerEntity, Long> {

	List<BorrowerEntity> findAll();

	BorrowerEntity findByFirstNameAndLastName(String firstName, String lastName);

}
