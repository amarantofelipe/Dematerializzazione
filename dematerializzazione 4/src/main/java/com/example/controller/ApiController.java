package com.example.controller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.ApiModel;
import com.example.model.IndiceBollette;
import com.example.repository.ApiRepo;


@EnableAutoConfiguration
@RestController()
@RequestMapping("/api")
public class ApiController {

	@Autowired
	ApiRepo apiRepo;
	
	@Autowired
    private DataConfig databaseUpdates;
	
	private static Path currentRelativePath = Paths.get("");
	private static String filePath = ((java.nio.file.Path) currentRelativePath).toAbsolutePath().toString();
	
	
	@PostMapping("/dema")
	public void dema(@RequestBody String  file) {
		System.out.println("Progetto ok ");
	}
	
	
	@PostMapping("/csv")
	public List<List<String>> read(@RequestBody String file) {
		byte[] x = Base64.getDecoder().decode(file.getBytes()); // convertiamo il file in base 64 in un array di bytes
		List<List<String>> records = new ArrayList<>(); // creiamo una lista che conterrà un altra lista di stringhe
		OutputStream out;
		
		try {
			File f = new File("/Users/felicemarano/java/open.csv"); // creiamo un filoe temporaneo 
			out = new FileOutputStream(f); // salviamo il file
			out.write(x); // scriviamlo i byte all interno del filee
			out.close();
			
			try (BufferedReader br = new BufferedReader(new FileReader("/Users/felicemarano/java/open.csv"))) { // leggiamo il file
			    String line;
			    while ((line = br.readLine()) != null) { // ledggiamo ogni riga del file
			        String[] values = line.split(";"); // separiamo ad ogni punto e virgola
			        records.add(Arrays.asList(values)); // aggiungiamo alla lista ogni singolo valore
			    }
			// Gestiamo le eccezzioni
			}catch (IOException e) {
				e.printStackTrace();
			}
			
			f.delete(); // cancelliamo il file temporaneo
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		IndiceBollette lb = new IndiceBollette();	// creiamo un nuovo indice per la bolletta	
		for (List<String> api : records) { // per ogni lista di stringhe contenuta in records
			
			ApiModel a = new ApiModel(api.get(0), api.get(1), api.get(2), lb); // creiamo un nuovo oggetto di tipo api model
			
			apiRepo.save(a); // salvaimo l'oggetto nel database

			databaseUpdates.alterTable("tab"+api.get(0));  // riniominiamo e creiamo una nuova tabella
		}
		
		return records; // ritorniamo i valori a chi ha fatto la richiesta
	}
	
}
