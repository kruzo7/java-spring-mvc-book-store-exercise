package pl.exercise.spring.mvc.restful.controller.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.time.LocalDateTime;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import pl.exercise.spring.mvc.assembler.AuthorAssembler;
import pl.exercise.spring.mvc.assembler.BookAssembler;
import pl.exercise.spring.mvc.configuration.AppConf;
import pl.exercise.spring.mvc.dto.AuthorDto;
import pl.exercise.spring.mvc.dto.BookDto;
import pl.exercise.spring.mvc.restful.controller.BookRestController;
import pl.exercise.spring.mvc.service.BookService;

public class BookRestControllerTest {

	private MockMvc mockMvc;

	@Mock
	private BookService bookService;

	@Spy
	private AuthorAssembler authorAssembler;

	@Mock
	private BookAssembler bookAssembler;

	@InjectMocks
	private BookRestController bookRestController;

	@BeforeMethod
	private void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(bookRestController).build();
	}

	@Test
	public void testGet() throws Exception {

		AuthorDto authorDto = new AuthorDto();
		authorDto.setId(1L);
		authorDto.setFirstName("Lisa");
		authorDto.setLastName("Langdon");

		BookDto bookDto = new BookDto();
		bookDto.setAuthor(authorDto);
		bookDto.setBorrowDetails(Lists.newArrayList());
		bookDto.setCategories(Lists.newArrayList());
		bookDto.setCoverType("SOFT");
		bookDto.setId(1L);
		bookDto.setIsbn("234-12341234");
		bookDto.setNumberOfPages(2L);
		bookDto.setPublishDate(LocalDateTime.now());
		bookDto.setTitle("Test");

		Mockito.when(bookService.findOneEager(Mockito.anyLong())).thenReturn(bookDto);		

		this.mockMvc
				.perform(MockMvcRequestBuilders.get(AppConf.REST_API + "/book/1")
						.accept(MediaType.parseMediaType("application/json;charset=UTF8")))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF8"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Test"));

	}
}
