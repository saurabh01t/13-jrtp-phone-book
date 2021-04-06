package com.ait.pbm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ait.pbm.controller.ContactRestController;
import com.ait.pbm.entity.Contact;
import com.ait.pbm.exception.NoDataFoundException;
import com.ait.pbm.service.ContactService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jdk.nashorn.internal.ir.annotations.Ignore;

//@SpringBootTest
@WebMvcTest(value = ContactRestController.class)
class PhoneBookApplicationTests {

	//@Test
	void contextLoads() {
	}
	@MockBean
	private ContactService contactSrv;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AppProperties appProperties;
	
	@Test
	public void test_saveContact_1 () throws Exception {
		when(contactSrv.saveContact(Mockito.any())).thenReturn(true);
		
		Contact contact = new Contact(101, "Saurabh", "hello@gmail", "8907");
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			json = mapper.writeValueAsString(contact);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//new MockMvcRequestBuilders();
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders
				.post("/v1.0/phonebook")
				.contentType("application/json")
				.content(json);
		
		MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(201, status);
	}
	
	@Test
	public void test_saveContact_2 () throws Exception {
		when(contactSrv.saveContact(Mockito.any())).thenReturn(false);
		
		Contact contact = new Contact(101, "Saurabh", "hello@gmail", "8907");
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			json = mapper.writeValueAsString(contact);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//new MockMvcRequestBuilders();
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders
				.post("/v1.0/phonebook")
				.contentType("application/json")
				.content(json);
		
		MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(500, status);
	}
	@Test
	public void test_saveContact_3() throws Exception {
		//when(contactSrv.saveContact(Mockito.any())).thenThrow(RuntimeException.class);
		
		when(contactSrv.saveContact(Mockito.any())).thenThrow(NoDataFoundException.class);
		Contact c = new Contact(101, "Ashok", "ashok@in.com", "6301921");
		ObjectMapper objMapper = new ObjectMapper();
		String json = null;
		try {
			 json = objMapper.writeValueAsString(c);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.post("/v1.0/phonebook")
				.contentType("application/json").content(json);
		MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();
		MockHttpServletResponse httpServletResponse = mvcResult.getResponse();
		int status = httpServletResponse.getStatus();
		
		assertEquals(500, status);
	}

}
