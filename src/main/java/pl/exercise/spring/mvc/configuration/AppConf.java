package pl.exercise.spring.mvc.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConf {

	//TODO: not working
	//@Value("${server.port}")
	//public static String PORT;

	public static final String PAGE_SIZE = "20";

	// zero-based index
	public static final String DEFAULT_PAGE_NUMBER = "0";

	public static final String REST_API_URL = "http://localhost:8081";

	public static final String REST_API_VER = "/api/v1";

	public static final String REST_API = REST_API_URL + REST_API_VER;

	public static final String DATE_FORMAT = "yyyy-MM-dd";

	public static final String DATE_FORMAT_JSON = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

	public static final Long MOTHS_TO_RETURN_BOOK = 1L;
	
	public static final int SEQ_START = 10;

}
