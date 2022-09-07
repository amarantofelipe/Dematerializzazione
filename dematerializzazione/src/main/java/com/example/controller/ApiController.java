package com.example.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Fattura;
import com.example.repository.ApiRepo;

@RestController()
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	ApiRepo apiRepo;
	
	@PostMapping("/csv")
	public List<List<String>> read(@RequestBody String file) {
		byte[] x = Base64.getDecoder().decode(file.getBytes());
		List<List<String>> records = new ArrayList<>();
		OutputStream out;
		try {
			File f = new File("/Users/felicemarano/java/open.csv");
			out = new FileOutputStream(f);
			out.write(x);
			out.close();
			try (BufferedReader br = new BufferedReader(new FileReader("open.csv"))) {
			    String line;
			    while ((line = br.readLine()) != null) {
			        String[] values = line.split(";");
			        records.add(Arrays.asList(values));
			    }
			}catch (IOException e) {
				e.printStackTrace();
			}
			f.delete();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		    String path = "/Users/felicemarano/java/contatore.txt";
		    try {
		        File fileCont = new File(path);
		        
		        if (fileCont.exists()) {
		        
		        	System.out.println("Il file " + path + " esiste");
		        	
		        	FileReader fr = new FileReader(file);
		        	
		        	
		        }
		        	
		        
		        else if (fileCont.createNewFile())
		            System.out.println("Il file " + path + " è stato creato");
		        
		        else
		            System.out.println("Il file " + path + " non può essere creato");
		        
		    } catch (IOException e) {
		    	
		        e.printStackTrace();
		    }
		
		
		for (List<String> list : records) {
			
			Fattura fat = new Fattura(list.get(0), list.get(1), list.get(2));
			
			apiRepo.save(fat);
		}
		
		return records;
	}

}
