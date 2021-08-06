package pl.exercise.spring.mvc.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import pl.exercise.spring.mvc.dto.BorrowerDto;
import pl.exercise.spring.mvc.entity.BorrowerEntity;

@Component
public class BorrowerAssembler {

	public BorrowerDto toDto(BorrowerEntity borrower) {
		BorrowerDto borrowerDto = new BorrowerDto();
		borrowerDto.setId(borrower.getId());
		borrowerDto.setFirstName(borrower.getFirstName());
		borrowerDto.setLastName(borrower.getLastName());
		return borrowerDto;
	}

	public List<BorrowerDto> toDtos(List<BorrowerEntity> borrowers) {
		return borrowers.stream().map(this::toDto).collect(Collectors.toList());
	}
}
