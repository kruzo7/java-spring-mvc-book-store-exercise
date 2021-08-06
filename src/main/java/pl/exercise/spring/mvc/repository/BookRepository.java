package pl.exercise.spring.mvc.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.exercise.spring.mvc.entity.BookCategoryType;
import pl.exercise.spring.mvc.entity.BookEntity;

//http://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repository-query-keywords

@Repository
@Transactional
public interface BookRepository extends JpaRepository<BookEntity, Long> {

	List<BookEntity> findAll();

	BookEntity findByAuthorIdAndIsbn(Long authorId, String isbn);

	Page<BookEntity> findDistinctBookEntityByTitleIsLikeAndIsbnIsLikeAndAuthor_FirstNameIsLikeAndAuthor_LastNameIsLikeAndCategoriesIsIn(
			String title, String isbn, String firstName, String lastName, List<BookCategoryType> categories,
			Pageable pageable);

	@Query("select distinct b from BookEntity b join b.author a left join b.categories c "
			+ "where (b.title like %:title%) and (b.isbn like %:isbn%) and (a.firstName like %:firstName%) "
			+ "and (a.lastName like %:lastName%) and (c in (:categories)) ")
	Page<BookEntity> search(@Param("title") String title, @Param("isbn") String isbn,
			@Param("firstName") String firstName, @Param("lastName") String lastName,
			@Param("categories") List<BookCategoryType> categories, Pageable pageable);

	@Override
	@EntityGraph(attributePaths = { "BookEntity.borrowDetails", "BookEntity.categories" }, type = EntityGraphType.LOAD)
	BookEntity getOne(Long id);

}
