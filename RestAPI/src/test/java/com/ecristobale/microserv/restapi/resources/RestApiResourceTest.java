package com.ecristobale.microserv.restapi.resources;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ecristobale.microserv.restapi.models.MyEntity;
import com.ecristobale.microserv.restapi.resources.RestApiResource;
import com.ecristobale.microserv.restapi.services.IMyService;

@RunWith(MockitoJUnitRunner.class)
public class RestApiResourceTest {

	@Mock
	private IMyService myService;
	
	private MockMvc mockMvc;
	
	@Spy
	@InjectMocks
	private RestApiResource controller = new RestApiResource();
	
	@Before
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void testGetEntidadById() throws Exception {
		MyEntity entity = new MyEntity();
		entity.setId((long) 1);
		entity.setName("Entity name");
		entity.setDescription("Entity description");
		Mockito.when(myService.getEntidadById(Mockito.anyLong())).thenReturn(entity);
		mockMvc.perform(MockMvcRequestBuilders.get("/restapi/entidad/1").header("Authorization", "345pasdf2340986asdf")).andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
	}
	
	@Test
	public void testDeleteById() throws Exception {
		MyEntity entity = new MyEntity();
		entity.setId((long) 1);
		entity.setName("Entity name");
		entity.setDescription("Entity description");
		Mockito.when(myService.getEntidadById(Mockito.anyLong())).thenReturn(entity);
		Mockito.when(myService.deleteMyEntity(Mockito.anyLong())).thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders.delete("/restapi/entidad/1").header("Authorization", "345pasdf2340986asdf")).andExpect(MockMvcResultMatchers.status().is(200));
	}

}
