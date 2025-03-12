package cl.genesiscastillo.previred;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.multipart.MultipartFile;

import cl.genesiscastillo.previred.controllers.EmpleadoController;
import cl.genesiscastillo.previred.controllers.NominasController;
import cl.genesiscastillo.previred.services.EmpleadoService;

public class NominasControllerTest {

	EmpleadoService empleadoService = Mockito.mock(EmpleadoService.class);
	
	@Test
	public void test6() throws InterruptedException, ExecutionException {
		MultipartFile multipartFile = Mockito.mock(MultipartFile.class );
		
		when(multipartFile.isEmpty()).thenReturn(Boolean.TRUE);
		
		NominasController nominasController = new NominasController(empleadoService );
		Assertions.assertNotNull(nominasController.subirArchivo(multipartFile));
			
	}

	@Test
	public void test7() throws InterruptedException, ExecutionException, Exception {
		MultipartFile multipartFile = Mockito.mock(MultipartFile.class );
		
		when(multipartFile.isEmpty()).thenReturn(Boolean.FALSE);
		Path path = Paths.get("data/empleados_100002.csv");

		when(multipartFile.getInputStream()).thenReturn(Files.newInputStream(path));
		
		NominasController nominasController = new NominasController(empleadoService );
		Assertions.assertNotNull(nominasController.subirArchivo(multipartFile));
			
	}
	
	@Test
	public void test8() throws InterruptedException, ExecutionException, Exception {
		MultipartFile multipartFile = Mockito.mock(MultipartFile.class );
		
		when(multipartFile.isEmpty()).thenReturn(Boolean.FALSE);
		
		when(multipartFile.getInputStream()).thenThrow(new IOException("ERROR"));
		
		NominasController nominasController = new NominasController(empleadoService);
		
		Assertions.assertNotNull(nominasController.subirArchivo(multipartFile));
			
	}

}
