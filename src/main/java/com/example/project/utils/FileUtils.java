package com.example.project.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileUtils {
	
	@Value("${visita.registro}")
	private String fichero;
	
	public boolean writeVisitantesFile(String texto){
		
		if(texto == null || texto.split(":").length != 5)
			return false;
		try {
			List<String> lines = new ArrayList<String>();
			lines.add(texto);
		    Files.write(Paths.get(fichero), lines, StandardOpenOption.APPEND);
		    return true;
		}catch (IOException e) {
			return false;
		}
	}
	
	public List<String> readVisitantesFile(){
		
		List<String> lines = new ArrayList<String>();
		
		try {
			lines = Files.readAllLines(Paths.get(fichero));
			return lines;
		}catch (IOException e) {
			return null;
		}
		
	}
	
	public boolean updateVisitantesFile(String texto){
		
		String ip = texto.split(":")[1];
		int i = 0;
		boolean existe = false;
		
		try {
			List<String> lines = Files.readAllLines(Paths.get(fichero));
			for(String linea : lines){
				if(linea.contains(ip)){
					existe = true;
					break;
				}
				i++;
			}
			if(existe){
				lines.remove(i);
				Files.write(Paths.get(fichero), lines, StandardOpenOption.TRUNCATE_EXISTING);	
			}
			lines = new ArrayList<String>();
			lines.add(texto);
		    Files.write(Paths.get(fichero), lines, StandardOpenOption.APPEND);
		    return true;
		}catch (IOException e) {
			return false;
		}
	}

}
