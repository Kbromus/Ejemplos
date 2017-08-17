package ejemplo.utils;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.project.SpringBootExample;
import com.example.project.model.Visitante;
import com.example.project.utils.Utils;
import com.example.project.utils.VisitanteUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes={SpringBootExample.class})
public class VisitanteUtilsTest {
	
	private Visitante visita;
	private Visitante visita_nueva;
	
	private List<Visitante> listadoVisitas;
	
	@Autowired
	private VisitanteUtils vutils;
	private Date desde;
	private Date hasta;
	
	@Before
	public void init(){
		listadoVisitas = vutils.recogeVisitantes();
		visita = listadoVisitas.get(0);
		visita_nueva = Visitante.newBuilder()
				.withEmpresa("Empresa")
				.withIp(Utils.generateIP())
				.withNombre("Manuel")
				.withAcceso(Utils.generaStrFechaHoy()).build();
		try {
			desde = Utils.generaFechaFormato("07-08-2017_14-00-00");
			hasta = Utils.generaFechaFormato("31-12-2017_23-59-59");
		} catch (ParseException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void escribeRegistroVisitanteTest(){
		assertTrue(vutils.escribeRegistroVisitante(visita_nueva));
		List<Visitante> listadoModificado = vutils.recogeVisitantes();
		assertTrue(listadoModificado.size() == listadoVisitas.size()+1);
	}
	@Test
	public void updateRegistroVisitanteTest(){
		assertTrue(vutils.escribeRegistroVisitante(visita));
		List<Visitante> listadoModificado = vutils.recogeVisitantes();
		assertTrue(listadoModificado.size() == listadoVisitas.size());
	}
	@Test
	public void recogeVisitantesTest(){
		assertTrue(listadoVisitas != null);
		assertTrue(listadoVisitas.size() != 0);
	}
	@Test
	public void recogeVisitantesHoyTest(){
		List<Visitante> visitasHoy = vutils.listarVisitasHoy();
		assertTrue(visitasHoy != null);
		//assertTrue(visitasHoy.size() == listadoVisitas.size());
	}
	@Test
	public void recogeVisitantesDesdeTest(){
		List<Visitante> visitasDesde = vutils.listarVisitasDesde(desde);
		assertTrue(visitasDesde != null);
		//assertTrue(visitasDesde.size() == listadoVisitas.size());
	}
	@Test
	public void recogeVisitantesHastaTest(){
		List<Visitante> visitasHasta = vutils.listarVisitasHasta(hasta);
		assertTrue(visitasHasta != null);
		//assertTrue(visitasHasta.size() == listadoVisitas.size());
	}
	@Test
	public void buscarVisitanteTest(){
		Visitante encontrado = vutils.buscarVisitante(visita.getIp());
		assertTrue(encontrado.getEmpresa().equals(visita.getEmpresa()));
		assertTrue(encontrado.getIp().equals(visita.getIp()));
		assertTrue(encontrado.getNombre().equals(visita.getNombre()));
	}

}
