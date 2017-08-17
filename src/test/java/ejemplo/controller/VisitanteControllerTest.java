package ejemplo.controller;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.project.SpringBootExample;
import com.example.project.controller.VisitanteController;
import com.example.project.model.Visitante;
import com.example.project.services.VisitanteService;
import com.fasterxml.jackson.databind.ObjectMapper;

import ejemplo.TestUtils;

@RunWith(SpringRunner.class)
@WebMvcTest(VisitanteController.class)
@ContextConfiguration(classes={SpringBootExample.class})
public class VisitanteControllerTest {
	
	/** The mock mvc. */
	@Autowired
    private MockMvc mockMvc;
	
	/** The object mapper. */
    @Autowired
    private ObjectMapper objectMapper;
    
    @MockBean
    private VisitanteService service;
    
    @Before
    public void init() {

    }
    
    @Test
    public void getVisitanteTest(){
    	try {
    		
			this.mockMvc.perform(get("/visitor/home").contentType(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$").exists())
			        .andExpect(jsonPath("$.ip").exists());
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
    }
    
    @Test
    public void addVisitanteTest(){
    
	    try {
	    	
	    	Visitante generado = TestUtils.generaVisita();
	    	
	    	when(service.registroVisita(anyObject())).thenReturn(generado);
	    	
			this.mockMvc.perform(post("/visitor/add").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(objectMapper.writeValueAsString(generado)))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$").exists())
						.andExpect(jsonPath("$.nombre").exists())
				        .andExpect(jsonPath("$.ip").exists())
				        .andExpect(jsonPath("$.empresa").exists())
				        .andExpect(jsonPath("$.nombre").isString())
				        .andExpect(jsonPath("$.ip").isString())
				        .andExpect(jsonPath("$.nombre").value(generado.getNombre()))
				        .andExpect(jsonPath("$.empresa").value(generado.getEmpresa()))
				        .andExpect(jsonPath("$.ip").value(generado.getIp()));		

		} catch (Exception e) {
			fail(e.getMessage());
		}
    }
    
    @Test
    public void findVisitanteTest(){
    	try {
    		
    		Visitante generado = TestUtils.generaVisitaExistente();
    		
    		when(service.buscaVisita(anyString())).thenReturn(generado);
    		
			this.mockMvc.perform(get("/visitor/find/"+generado.getIp()).contentType(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$").exists())
					.andExpect(jsonPath("$.nombre").exists())
			        .andExpect(jsonPath("$.ultimoAcceso").exists())
			        .andExpect(jsonPath("$.empresa").exists())
			        .andExpect(jsonPath("$.nombre").isString())
			        .andExpect(jsonPath("$.ultimoAcceso").isString())
			        .andExpect(jsonPath("$.nombre").value(generado.getNombre()))
			        .andExpect(jsonPath("$.empresa").value(generado.getEmpresa()));
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
    }
    
    @Test
    public void fetchVisitantesTest(){
    	
    	when(service.listadoVisitas()).thenReturn(TestUtils.generaListadoVisitas());
    	
    	try {
			this.mockMvc.perform(get("/visitor/all").contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").exists())
			.andExpect(jsonPath("$").isArray())
			.andExpect(jsonPath("$").isNotEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
    }
    
    @Test
    public void filterVisitantesTest(){
    		
    	try {
    		when(service.filtrarVisitas(anyInt(), anyString())).thenReturn(TestUtils.generaListadoVisitas());
    		
			this.mockMvc.perform(get("/visitor/filter/2/01-08-2017_08-00-01").contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").exists())
			.andExpect(jsonPath("$").isArray())
			.andExpect(jsonPath("$").isNotEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
    }

}
