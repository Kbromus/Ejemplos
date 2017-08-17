package ejemplo.services;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.project.SpringBootExample;
import com.example.project.model.Visitante;
import com.example.project.services.VisitanteService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes={SpringBootExample.class})
public class VisitanteServiceTest {
	
	private Visitante visita;
	
	@Autowired
	private VisitanteService service;
	
	@Before
	public void setUpTest(){
		visita = Visitante.newBuilder().withEmpresa("Company").withIp("50.1.0.2").withNombre("George").build();
	}
	
	@Test
	public void registroVisitaTest(){
		
		Visitante insertado = service.registroVisita(visita);
		assertTrue(insertado != null);
		assertTrue(insertado.getEmpresa() != null && insertado.getEmpresa().equals(visita.getEmpresa()));
		assertTrue(insertado.getIp() != null && insertado.getIp().equals(visita.getIp()));
		assertTrue(insertado.getNombre() != null && insertado.getNombre().equals(visita.getNombre()));
	}

}
