package pl.exercise.spring.mvc.restful.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.exercise.spring.mvc.configuration.AppConf;
import pl.exercise.spring.mvc.dto.BorrowerDto;
import pl.exercise.spring.mvc.service.BorrowerService;

@RestController
@RequestMapping(AppConf.REST_API_VER + "/borrower")
public class BorrowerRestController extends ApplicationApiRestControllerAbstract<BorrowerDto>
		implements ApplicationApiRestController<BorrowerDto> {

	@Autowired
	BorrowerService borrowerService;

	@RequestMapping(method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public List<BorrowerDto> findAll() {
		return this.borrowerService.findAll();
	}

	public BorrowerDto create(BorrowerDto dto) throws ParseException {
		// TODO implemant later if needed
		return null;
	}

	public BorrowerDto get(Long id) {
		// TODO implemant later if needed
		return null;
	}

	public BorrowerDto update(Long id, BorrowerDto type) throws ParseException {
		// TODO implemant later if needed
		return null;
	}

	public void delete(Long id) {
		// TODO implemant later if needed

	}
}
