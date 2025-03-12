package cl.genesiscastillo.previred.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cl.genesiscastillo.previred.dtos.EmpleadoDTO;
import cl.genesiscastillo.previred.entities.Empleado;
import cl.genesiscastillo.previred.services.EmpleadoService;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmpleadoController.class);
	
	private EmpleadoService empleadoService;
	
    private ValidatorFactory factory;

	
	public EmpleadoController(EmpleadoService empleadoService , ValidatorFactory factory) {
		this.empleadoService = empleadoService;
		this.factory = factory;
	}
	
	@GetMapping("/")
	public Iterable<Empleado> obtenerTodosEmpleados(){
		return empleadoService.obtenerTodosEmpleados();
	}
	
	@PostMapping("/")
	public ResponseEntity<Set<String>> grabarEmpleado(@RequestBody EmpleadoDTO empleadoDTO) {
		LOGGER.info(String.format("grabar empleado : %s" , empleadoDTO));
		
        Validator validator = factory.getValidator();
        
        Set<ConstraintViolation<EmpleadoDTO>> violations = validator.validate(empleadoDTO);
        Set<String> errores= new HashSet<String>();
        for (ConstraintViolation<EmpleadoDTO> violation : violations) {
             errores.add(violation.getMessage());
        }
        errores = empleadoService.validarDatosEntrada(empleadoDTO , errores );
        
        if(empleadoService.existeRutDni(empleadoDTO)) {
        	errores.add("Empleado ya existe con el rutdni = "+empleadoDTO.getRutdni());
        }
        if(errores.isEmpty()) {
        	Empleado empleado =	empleadoService.guardarEmpleado(empleadoDTO);
        	errores.add("Empleado guardado correctamente, id = "+empleado.getId());
        	return ResponseEntity.ok(errores);
        }
        else {
        	return ResponseEntity.badRequest().body(errores);
        }
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarEmpleado(@PathVariable Integer id) {
		LOGGER.info("eliminarEmpleado id = "+id);
		if(empleadoService.eliminarEmpleado(id)) {
			return ResponseEntity.ok("Empleado eliminado correctamente. id = "+id);
		}else {
			return ResponseEntity.badRequest().body("No se encontro el empleado con id = "+id);	
		}
	}
}
