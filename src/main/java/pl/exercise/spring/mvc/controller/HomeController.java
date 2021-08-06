package pl.exercise.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController extends ApplicationControllerAbstract {

	@RequestMapping(path = { "/", "/home", "/index.html" }, method = RequestMethod.GET)
	public String index(Model model) {
		return "index";
	}

}
