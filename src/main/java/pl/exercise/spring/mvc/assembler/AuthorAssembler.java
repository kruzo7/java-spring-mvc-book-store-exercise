package pl.exercise.spring.mvc.assembler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import pl.exercise.spring.mvc.dto.AuthorDto;
import pl.exercise.spring.mvc.entity.AuthorEntity;
import pl.exercise.spring.mvc.entity.LibraryItemBase;

@Component
public class AuthorAssembler {

	public AuthorDto toDto(AuthorEntity author) {
		AuthorDto authorDto = new AuthorDto();
		authorDto.setId(author.getId());
		authorDto.setFirstName(author.getFirstName());
		authorDto.setLastName(author.getLastName());
		return authorDto;
	}

	public List<AuthorDto> toDtos(List<AuthorEntity> authors) {
		return authors.stream().map(this::toDto).collect(Collectors.toList());
	}

	public AuthorEntity toEntity(AuthorDto authorDto) {
		//TODO: add books later if needed
		AuthorEntity authorEntity = new AuthorEntity(authorDto.getFirstName(), authorDto.getLastName());
		authorEntity.setLibraryItems(new ArrayList<LibraryItemBase>());
		return authorEntity;
	}
}
