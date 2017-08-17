package ejemplo.utils;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.project.SpringBootExample;
import com.example.project.utils.FileUtils;
import com.example.project.utils.Utils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes={SpringBootExample.class})
public class FileUtilsTest {
	
	private String texto;
	
	@Autowired
	private FileUtils futils;
	
	@Before
	public void init(){
		texto = "User:"+Utils.generateIP()+":Random:1:"+Utils.generaStrFechaHoy();
	}
	
	@Test
	public void writeVisitantesFileTest(){
		assertTrue(futils.writeVisitantesFile(texto));
	}
	@Test
	public void readVisitantesFileTest(){
		assertTrue(futils.readVisitantesFile() != null);
		assertTrue(futils.readVisitantesFile().size() != 0);
	}
	@Test
	public void updateVisitantesFileTest(){
		String update = futils.readVisitantesFile().get(0);
		String [] elem = update.split(":");
		elem[4] = Utils.generaStrFechaHoy();
		elem[3] = String.valueOf(Integer.parseInt(elem[3])+1);
		update = String.join(":", elem);
		assertTrue(futils.updateVisitantesFile(update));
	}

}
