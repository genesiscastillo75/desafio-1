package cl.genesiscastillo.previred;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import cl.genesiscastillo.previred.dtos.EmpleadoDTO;
import cl.genesiscastillo.previred.entities.Empleado;
import cl.genesiscastillo.previred.repositories.EmpleadoRepository;
import cl.genesiscastillo.previred.services.EmpleadoService;

public class EmpleadoServiceTest {
	
	EmpleadoRepository empleadoRepository = Mockito.mock(EmpleadoRepository.class );
	

	@Test
	public void test5() throws Exception {
		
		String[] data = new String[7];
		data[0] = "1-9";
		data[1] = "cesar";
		data[2] = "castillo";
		data[3] = "analista";
		data[4] = "100000";
		data[5] = "30000";
		data[6] = "30000";
		
		Empleado empleado = Empleado.builder()
				.rutdni(data[0])
				.nombre(data[1])
				.apellido(data[2])
				.cargo(data[3])
				.build();
		
		Empleado empleado2 = Empleado.builder().build();
		empleado2.setId(1);

		when(empleadoRepository.save(empleado)).thenReturn(empleado2);		
		
		List<String[]> datas = new ArrayList<String[]>();
		datas.add(data);
		
		EmpleadoService empleadoService = new EmpleadoService(empleadoRepository);
		
		Assertions.assertNotNull(empleadoService.procesarSueldoFinal(datas));
	}

	
	@Test
	public void test4() {
		
		EmpleadoService empleadoService = new EmpleadoService(empleadoRepository);
		
		Set<String> errores = new HashSet<String>();
		EmpleadoDTO empleadoDTO = new EmpleadoDTO();
		empleadoDTO.setNombre("n");
		empleadoDTO.setApellido("a");
		empleadoDTO.setCargo("c");
		empleadoDTO.setRutdni("1-9");

		Assertions.assertNotNull(empleadoService.validarDatosEntrada(empleadoDTO, errores));
		
		empleadoDTO.setSalario(100L);
		empleadoDTO.setBono(100L);
		empleadoDTO.setDescuento(50L);

		Assertions.assertNotNull(empleadoService.validarDatosEntrada(empleadoDTO, errores));
		
		empleadoDTO.setBono(null);
		empleadoDTO.setDescuento(null);

		Assertions.assertNotNull(empleadoService.validarDatosEntrada(empleadoDTO, errores));
		
		empleadoDTO.setSalario(300L);
		empleadoDTO.setBono(100L);

		Assertions.assertNotNull(empleadoService.validarDatosEntrada(empleadoDTO, errores));
		
		empleadoDTO.setSalario(300L);
		empleadoDTO.setDescuento(500L);

		Assertions.assertNotNull(empleadoService.validarDatosEntrada(empleadoDTO, errores));

		empleadoDTO.setSalario(500L);
		empleadoDTO.setDescuento(300L);

		Assertions.assertNotNull(empleadoService.validarDatosEntrada(empleadoDTO, errores));

	}
	
	@Test
	public void test3() {
		
		EmpleadoDTO empleadoDTO = new EmpleadoDTO();
		empleadoDTO.setNombre("n");
		empleadoDTO.setApellido("a");
		empleadoDTO.setCargo("c");
		empleadoDTO.setRutdni("1-9");
		empleadoDTO.setSalario(200L);
		empleadoDTO.setBono(50L);
		empleadoDTO.setDescuento(50L);
	
		Empleado empleado = new Empleado();
		empleado.setId(1);
		
		when(empleadoRepository.save(empleado)).thenReturn(empleado);
		
		EmpleadoService empleadoService = new EmpleadoService(empleadoRepository);
		Assertions.assertNull(empleadoService.guardarEmpleado(empleadoDTO));
	}

	
	@Test
	public void test2() {
		EmpleadoDTO empleadoDTO = new EmpleadoDTO();
		empleadoDTO.setRutdni("1-9");
		
		Empleado empleado = new Empleado();
		Optional<Empleado> optional = Optional.of(empleado);
		
		when(empleadoRepository.findByRutdni(empleadoDTO.getRutdni())).thenReturn(optional);
		
		EmpleadoService empleadoService = new EmpleadoService(empleadoRepository);
		
		Assertions.assertTrue(empleadoService.existeRutDni(empleadoDTO));
		
	}
	
	@Test
	public void test1() {
		when(empleadoRepository.findAll()).thenReturn(new ArrayList<Empleado>());
		EmpleadoService empleadoService = new EmpleadoService(empleadoRepository);
		Assertions.assertEquals(empleadoService.obtenerTodosEmpleados().size() , 0);
	}

}
