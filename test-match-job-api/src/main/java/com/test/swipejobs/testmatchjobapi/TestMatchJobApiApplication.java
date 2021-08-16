package com.test.swipejobs.testmatchjobapi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TestMatchJobApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestMatchJobApiApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		RestTemplate restTemplate = builder.build();
		 List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();        
		 MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		 converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));        
		 messageConverters.add(converter);  
		 restTemplate.setMessageConverters(messageConverters); 
		 return restTemplate;
	}
	
	

}
