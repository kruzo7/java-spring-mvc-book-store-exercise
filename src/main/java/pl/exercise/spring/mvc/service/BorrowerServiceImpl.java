package pl.exercise.spring.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.exercise.spring.mvc.assembler.BorrowerAssembler;
import pl.exercise.spring.mvc.dto.BorrowerDto;
import pl.exercise.spring.mvc.entity.BorrowerEntity;
import pl.exercise.spring.mvc.repository.BorrowerRepository;

@Service
public class BorrowerServiceImpl implements BorrowerService {

	@Autowired
	private BorrowerRepository borrowerRepository;

	@Autowired
	private BorrowerAssembler borrowerAssembler;
	
	public BorrowerEntity findOne(Long id) {
		return this.borrowerRepository.findOne(id);
	}
	
	public List<BorrowerDto> findAll() {
		return this.borrowerAssembler.toDtos(this.borrowerRepository.findAll());
	}
}
