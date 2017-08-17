package com.example.project.services;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.project.model.Visitante;
import com.example.project.utils.Utils;
import com.example.project.utils.VisitanteUtils;

@Component
public class VisitanteService {
	
	@Autowired
	private VisitanteUtils visitanteUtils;
	
	
	/**
	 * Añadimos una visita en el registro de visitas
	 * @param visita objeto con los datos de la visita a registrar
	 * @return el objeto de entrada
	 */
	public Visitante registroVisita (Visitante visita){
		
		if(visitanteUtils.escribeRegistroVisitante(visita)){
			return visita;
		}else{
			return null;
		}
		
	}
	/**
	 * Buscamos en el documento de visitas una visita por su clave primaria
	 * @param ip desde donde se realiza la visita
	 * @return visita encontrada
	 */
	public Visitante buscaVisita(String ip){
		return visitanteUtils.buscarVisitante(ip);	
	}
	/**
	 * Metodo que lista todas las visitas en el registro
	 * @return
	 */
	public List<Visitante> listadoVisitas(){
		return visitanteUtils.recogeVisitantes();
	}
	/**
	 * 
	 * @param modo indicamos el modo de filtrado de visitas 1: las del dia de hoy, 2: filtro desde.., 3:filtro hasta..
	 * @param strfecha fecha en formato de la aplicacion (dd-mm-aaaa/hh-mm-ss)
	 * @return listado con las visitas filtrado en el modo seleccionado
	 * @throws ParseException si se introduce una fecha erronea
	 */
	public List<Visitante> filtrarVisitas(int modo, String strfecha) throws ParseException{
		
		Date fecha = Utils.generaFechaFormato(strfecha);
		
		switch(modo){
			case 1: return visitanteUtils.listarVisitasHoy();
			case 2: return visitanteUtils.listarVisitasDesde(fecha);
			case 3: return visitanteUtils.listarVisitasHasta(fecha);
			default: return visitanteUtils.listarVisitasHoy();
		}
		
	}

}
