package com.example.project.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.project.model.Visitante;

@Component
public class VisitanteUtils {
	
	private Map<String,Visitante> indice = new HashMap<String,Visitante>();
	
	@Autowired
	private FileUtils utils;
	
	private static String registroVisitante(Visitante visita){
		visita.setUltimoAcceso(Utils.generaStrFechaHoy());
		visita.setIp(Utils.formateaIP(visita.getIp()));
		return visita.toString();
	}
	
	public boolean escribeRegistroVisitante(Visitante visita){
		
		Visitante registrado = buscarVisitante(Utils.formateaIP(visita.getIp()));
		
		if(registrado == null){
			visita.setAportaciones(1);
			boolean escrito = utils.writeVisitantesFile(registroVisitante(visita));
			
			if(escrito){
				indice.put(Utils.formateaIP(visita.getIp()), visita);
			}
					
			return escrito;
		}else{
			visita.setAportaciones(registrado.getAportaciones()+1);
			return utils.updateVisitantesFile(registroVisitante(visita));
		}
	}
	
	public List<Visitante> recogeVisitantes(){
		
		List<Visitante> visitantes = new ArrayList<Visitante>();
		
		for (String linea : utils.readVisitantesFile()){
			
			String [] datos = linea.split(":");
			
			//si hay lineas corruptas no creamos objeto
			if(datos == null || datos.length != 5)
				continue;
			
			Visitante visita = creaVisitaDesdeRegistro(linea);
			
			if(!indice.containsKey(visita.getIp())){
				indice.put(visita.getIp(), visita);
			}
			
			visitantes.add(visita);
		}
		return visitantes;
		
	}
	
	public List<Visitante> listarVisitasHoy(){
		
		List<Visitante> visitantes = new ArrayList<Visitante>();
		
		for (String linea : utils.readVisitantesFile()){
			
			String [] datos = linea.split(":");
			
			//si hay lineas corruptas no creamos objeto asociado
			if(datos == null || datos.length != 5)
				continue;
			
			try {
				if(Utils.modificadoHoy(new Date(), datos[4]) == 0){
					Visitante visita = creaVisitaDesdeRegistro(linea);
					
					if(!indice.containsKey(visita.getIp())){
						indice.put(visita.getIp(), visita);
					}
					
					visitantes.add(visita);
				}//fin if comparafechas
				
				
			} catch (ParseException e) {
				return visitantes;
			}//fin try-catch
			
			
		}//fin del for
		return visitantes;
		
	}
	
	public List<Visitante> listarVisitasDesde(Date desde){
		
		List<Visitante> visitantes = new ArrayList<Visitante>();
		
		for (String linea : utils.readVisitantesFile()){
			
			String [] datos = linea.split(":");
			
			//si hay lineas corruptas no creamos objeto asociado
			if(datos == null || datos.length != 5)
				continue;
			
			try {
				if(Utils.comparaFechas(desde, datos[4]) > 0){
					Visitante visita = creaVisitaDesdeRegistro(linea);
					
					if(!indice.containsKey(visita.getIp())){
						indice.put(visita.getIp(), visita);
					}
					
					visitantes.add(visita);
				}//fin if comparafechas
				
				
			} catch (ParseException e) {
				return visitantes;
			}//fin try-catch
			
			
		}//fin del for
		return visitantes;
		
	}

	public List<Visitante> listarVisitasHasta(Date hasta){
	
	List<Visitante> visitantes = new ArrayList<Visitante>();
	
	for (String linea : utils.readVisitantesFile()){
		
		String [] datos = linea.split(":");
		
		//si hay lineas corruptas no creamos objeto asociado
		if(datos == null || datos.length != 5)
			continue;
		
		try {
			if(Utils.comparaFechas(hasta, datos[4]) < 0){
				Visitante visita = creaVisitaDesdeRegistro(linea);
				
				if(!indice.containsKey(visita.getIp())){
					indice.put(visita.getIp(), visita);
				}
				
				visitantes.add(visita);
			}//fin if comparafechas
			
			
		} catch (ParseException e) {
			return visitantes;
		}//fin try-catch
		
		
	}//fin del for
	return visitantes;
	
	}
	
	public Visitante buscarVisitante(String ip){
		if(indice.size() == 0)
			recogeVisitantes();
		if(indice.containsKey(ip)){
			return indice.get(ip);
		}
		return null;
	}

	private Visitante creaVisitaDesdeRegistro(String linea) {
		
		String [] datos = linea.split(":");
		
		int entradas = Integer.parseInt(datos[3]);
		
		return Visitante.newBuilder().withNombre(datos[0]).
				withIp(datos[1]).
				withEmpresa(datos[2]).
				withAportaciones(entradas).
				withAcceso(datos[4]).build();
	}

	/**
	 * @return the indice
	 */
	public Map<String, Visitante> getIndice() {
		return indice;
	}

}
