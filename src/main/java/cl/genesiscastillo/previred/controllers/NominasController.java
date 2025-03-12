package cl.genesiscastillo.previred.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cl.genesiscastillo.previred.services.EmpleadoService;

@RestController
@RequestMapping("/api/nominas")
public class NominasController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NominasController.class);
	
	private EmpleadoService empleadoService;
	
	public NominasController(EmpleadoService empleadoService) {
		this.empleadoService = empleadoService;
	}

    @PostMapping("/calcular")
    public ResponseEntity<List<String[]>> subirArchivo(@RequestParam("archivo") MultipartFile archivo) throws InterruptedException, ExecutionException {
    	LOGGER.info("subirArchivo ");
    	
        if (archivo.isEmpty()) {
        	return ResponseEntity.badRequest().body(null);
        }
        try {
            List<String[]> sueldos = procesarCSV(archivo);
            return ResponseEntity.ok(sueldos);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
   	
	private List<String[]> procesarCSV(MultipartFile archivo) throws IOException, InterruptedException, ExecutionException {
	    List<String[]> sueldos = new ArrayList<>();
        List<String[]> sueldos2 = new ArrayList<>();	    
	    try (BufferedReader reader = new BufferedReader(new InputStreamReader(archivo.getInputStream()))) {
	        String linea;
	        int contador = 0;
	        while ((linea = reader.readLine()) != null) {
	            String[] valores = linea.split(",");
	            if( contador == 0 ) {
	            	contador++;
	            	continue;
	            }
	            sueldos.add(valores);
	        }
	        sueldos2 = empleadoService.procesarSueldoFinal(sueldos);
	    }
	    return sueldos2;
	}

	
}
