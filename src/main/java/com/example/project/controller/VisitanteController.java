package com.example.project.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.exception.VisitanteFilterException;
import com.example.project.model.BadParametersResponse;
import com.example.project.model.Visitante;
import com.example.project.services.VisitanteService;
import com.example.project.utils.VisitanteUtils;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/visitor")
public class VisitanteController {
	
	@Autowired
	public VisitanteService service;
	
	@CrossOrigin
    @ApiOperation(value = "Muestra datos del usuario que se conecta")
    @RequestMapping(method = RequestMethod.GET, path = "/hello",
    produces = MediaType.APPLICATION_JSON_VALUE)
	public String getVisitante(HttpServletRequest request){
		
		Visitante visita = Visitante.newBuilder().withIp(request.getRemoteAddr()).
				withEmpresa("None").withNombre("Anonymous").build();
		
		service.registroVisita(visita);
		
		return "Bienvenido a la web: "+ visita.toString();
	}
	
	@CrossOrigin
    @ApiOperation(value = "Añade una visita en el registro", response = Visitante.class)
    @RequestMapping(method = RequestMethod.POST, path = "/add",
    produces = MediaType.APPLICATION_JSON_VALUE)
	public Visitante addVisitante(@RequestBody Visitante visita, HttpServletRequest request){
		return service.registroVisita(visita);
	}
	
	@CrossOrigin
	@ApiOperation(value = "Busca una visita en el registro", response = Visitante.class)
	@RequestMapping(method = RequestMethod.GET, path = "/find/{ip_busqueda:.+}",
		    produces = MediaType.APPLICATION_JSON_VALUE)
	public Visitante findVisitante(@PathVariable(name = "ip_busqueda") String ip, HttpServletRequest request){
		return service.buscaVisita(ip);
	}
	
	@CrossOrigin
	@ApiOperation(value = "Obtiene una lista con todas las visitas")
	@RequestMapping(method = RequestMethod.GET, path = "/all",
		    produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Visitante> fetchVisitantes(HttpServletRequest request){
		return service.listadoVisitas();
	}
	
	@CrossOrigin
	@ApiOperation(value = "Obtiene una lista con todas las visitas filtrado en uno de los modos configurados", 
	notes = "1. Registros de hoy, 2.registros con fecha desde, 3. ficheros con fecha hasta, por defecto filtra las de hoy")
	 @ApiResponses(
	            value = { @ApiResponse(code = 404, message = "Bad Parameters", response = BadParametersResponse.class) })
	@RequestMapping(method = RequestMethod.GET, path = "/filter/{modo}/{fecha}",
		    produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Visitante> filterVisitantes(@PathVariable(name = "modo") int modo,
			@PathVariable(name = "fecha") String fecha,
			HttpServletRequest request) throws VisitanteFilterException{
		try {
			return service.filtrarVisitas(modo, fecha);
		} catch (ParseException e) {
			throw new VisitanteFilterException(404, e.getMessage(), e);
		}
	}
}
