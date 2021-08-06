package pl.exercise.spring.mvc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import pl.exercise.spring.mvc.dto.BorrowerDto;
import pl.exercise.spring.mvc.entity.BorrowerEntity;

@Service
public interface BorrowerService {

	BorrowerEntity findOne(Long id);

	List<BorrowerDto> findAll();

}
