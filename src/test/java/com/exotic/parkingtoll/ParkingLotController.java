package com.exotic.parkingtoll;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class ParkingLotController {
	protected MockMvc mvc;
	@Autowired
	WebApplicationContext webApplicationContext;

	protected void setUp() {
		   mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	protected String mapToJson(Object obj) throws JsonProcessingException {
	   ObjectMapper objectMapper = new ObjectMapper();
	   return objectMapper.writeValueAsString(obj);
	}
	
	protected <T> T mapFromJson(String json, Class<T> clazz)
	   throws JsonParseException, JsonMappingException, IOException {
	   
	   ObjectMapper objectMapper = new ObjectMapper();
	   return objectMapper.readValue(json, clazz);
	}
	
	@Test
	public void testGetPrice() throws Exception {
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/price").accept(MediaType.APPLICATION_JSON)).andReturn();
	
	}
	@Test
	public void testFindCarSlot() throws Exception {
		CarType type = CarType.ELECTRIC20KW;
		String typeJson = mapToJson(type);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/slot").accept(MediaType.APPLICATION_JSON).content(typeJson)).andReturn();		
	}
	@Test
	public void testFreeSlot() throws Exception {
		Slot slot = new Slot();
		slot.setFree(false);
		String inputJson = this.mapToJson(slot);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/slot").accept(MediaType.APPLICATION_JSON)).andReturn();
			
	}
}
