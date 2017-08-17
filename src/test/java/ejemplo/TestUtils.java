package ejemplo;

import java.util.ArrayList;
import java.util.List;

import com.example.project.model.Visitante;
import com.example.project.utils.Utils;

public class TestUtils {
	
	public static Visitante generaVisita(){
		return Visitante.newBuilder().withNombre("UsuarioTest").
				withEmpresa("EmpresaTest").
				withIp(Utils.generateIP()).build();
	}
	
	public static Visitante generaVisitaExistente(){
		return Visitante.newBuilder().withNombre("User")
				.withEmpresa("Random")
				.withAcceso("07-08-2017_13-16-58")
				.withIp("62.137.37.39")
				.build();
	}

	public static List<Visitante> generaListadoVisitas() {
		List<Visitante> listado  = new ArrayList<Visitante>();
		
		listado.add(generaVisita());
		listado.add(generaVisitaExistente());
		
		return listado;
	}

}
