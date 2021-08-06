package pl.exercise.spring.mvc.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.exercise.spring.mvc.dto.BorrowDetailDto;
import pl.exercise.spring.mvc.entity.BorrowDetailEntity;

@Component
public class BorrowDetailAssembler {

	@Autowired
	private BorrowerAssembler borrowerAssembler;

	public BorrowDetailDto toDto(BorrowDetailEntity borrowDetail) {
		BorrowDetailDto detailDto = new BorrowDetailDto();
		detailDto.setId(borrowDetail.getId());
		detailDto.setBorrowDate(borrowDetail.getBorrowDate());
		detailDto.setIsReturned(borrowDetail.getIsReturned());
		detailDto.setMaxReturnDate(borrowDetail.getReturnDate());
		detailDto.setReturnDate(borrowDetail.getReturnDate());
		detailDto.setLibraryItemId(borrowDetail.getLibraryItem().getId());
		detailDto.setBorrower(this.borrowerAssembler.toDto(borrowDetail.getBorrower()));
		return detailDto;
	}

	public List<BorrowDetailDto> toDtos(List<BorrowDetailEntity> borrowDetails) {
		return borrowDetails.stream().map(this::toDto).collect(Collectors.toList());
	}

}
