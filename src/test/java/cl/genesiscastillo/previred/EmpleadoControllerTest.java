package cl.genesiscastillo.previred;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.multipart.MultipartFile;

import cl.genesiscastillo.previred.controllers.EmpleadoController;
import cl.genesiscastillo.previred.controllers.IndexController;
import cl.genesiscastillo.previred.dtos.EmpleadoDTO;
import cl.genesiscastillo.previred.entities.Empleado;
import cl.genesiscastillo.previred.services.EmpleadoService;

public class EmpleadoControllerTest {
	
	EmpleadoService empleadoService = Mockito.mock(EmpleadoService.class);
    ValidatorFactory factory = Mockito.mock(ValidatorFactory.class );

	@Test
	public void test2() {
		IndexController indexController = new IndexController();
		Assertions.assertEquals(indexController.index() , "index");
	}

	@Test
	public void test1() {
	    
	    Empleado empleado = new Empleado();
	    empleado.setNombre("n");
	    empleado.setApellido("a");
	    empleado.setCargo("c");
	    empleado.setRutdni("1-9");
	    empleado.setSalario(200L);
	    empleado.setBono(100L);
	    empleado.setDescuento(300L);
	    
	    List<Empleado> empleados = new ArrayList<Empleado>();
	    empleados.add(empleado);
	    
	    when(empleadoService.obtenerTodosEmpleados()).thenReturn(empleados);
		
		EmpleadoController empleadoController = new EmpleadoController(empleadoService , factory );
		
		Iterable<Empleado> empleados2 =	empleadoController.obtenerTodosEmpleados();
		
		Assertions.assertNotNull(empleados2);
		
	}
	
	@Test
	public void test3() {
		Integer id = 100;
		when(empleadoService.eliminarEmpleado(id)).thenReturn(Boolean.TRUE);
		EmpleadoController empleadoController = new EmpleadoController(empleadoService , factory );
		Assertions.assertNotNull(empleadoController.eliminarEmpleado(id));
	}
	
	@Test
	public void test4() {
		Integer id = 100;
		when(empleadoService.eliminarEmpleado(id)).thenReturn(Boolean.FALSE);
		EmpleadoController empleadoController = new EmpleadoController(empleadoService , factory );
		Assertions.assertNotNull(empleadoController.eliminarEmpleado(id));
	}
	
	@Test
	public void test6() throws InterruptedException, ExecutionException {
		MultipartFile multipartFile = Mockito.mock(MultipartFile.class );
		
		when(multipartFile.isEmpty()).thenReturn(Boolean.TRUE);
		
		EmpleadoController empleadoController = new EmpleadoController(empleadoService , factory );
		Assertions.assertNotNull(empleadoController.subirArchivo(multipartFile));
			
	}

	@Test
	public void test7() throws InterruptedException, ExecutionException, Exception {
		MultipartFile multipartFile = Mockito.mock(MultipartFile.class );
		
		when(multipartFile.isEmpty()).thenReturn(Boolean.FALSE);
		Path path = Paths.get("empleados_100002.csv");

		when(multipartFile.getInputStream()).thenReturn(Files.newInputStream(path));
		
		EmpleadoController empleadoController = new EmpleadoController(empleadoService , factory );
		Assertions.assertNotNull(empleadoController.subirArchivo(multipartFile));
			
	}
	
	@Test
	public void test8() throws InterruptedException, ExecutionException, Exception {
		MultipartFile multipartFile = Mockito.mock(MultipartFile.class );
		
		when(multipartFile.isEmpty()).thenReturn(Boolean.FALSE);
		
		when(multipartFile.getInputStream()).thenThrow(new IOException("ERROR"));
		
		EmpleadoController empleadoController = new EmpleadoController(empleadoService , factory );
		
		Assertions.assertNotNull(empleadoController.subirArchivo(multipartFile));
			
	}
	

	//@Test
	public void test5() {
		EmpleadoDTO empleado = new EmpleadoDTO();
	    empleado.setNombre("n");
	    empleado.setApellido("a");
	    empleado.setCargo("c");
	    empleado.setRutdni("1-9");
	    empleado.setSalario(200L);
	    empleado.setBono(100L);
	    empleado.setDescuento(300L);
	    
	    //Set<ConstraintViolation<EmpleadoDTO>> violations = new HashSet<ConstraintViolation<EmpleadoDTO>>(); 
	    when(factory.getValidator().validate(empleado)).thenReturn(Mockito.anySet());
	    
	    Empleado empleado2 = Empleado.builder()
	    		.apellido(empleado.getApellido())
	    		.nombre(empleado.getNombre())
	    		.rutdni(empleado.getRutdni())
	    		.cargo(empleado.getCargo())
	    		.salario(empleado.getSalario())
	    		.bono(empleado.getBono())
	    		.descuento(empleado.getDescuento()).build();
	    
	    empleado2.setId(1);
	    
	    Set<String> errores= new HashSet<String>();
		
		when(empleadoService.validarDatosEntrada(empleado, Mockito.anySet() )).thenReturn(errores);
		EmpleadoController empleadoController = new EmpleadoController(empleadoService , factory );
		
		Assertions.assertNotNull( empleadoController.grabarEmpleado(empleado));
	}
	
	
	
}
