package cl.genesiscastillo.previred.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cl.genesiscastillo.previred.dtos.EmpleadoDTO;
import cl.genesiscastillo.previred.entities.Empleado;
import cl.genesiscastillo.previred.repositories.EmpleadoRepository;

@Service
public class EmpleadoService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmpleadoService.class);

	private final EmpleadoRepository empleadoRepository;

	public EmpleadoService(EmpleadoRepository empleadoRepository) {
		this.empleadoRepository = empleadoRepository;
	}
	
	public boolean existeRutDni(EmpleadoDTO empleadoDTO) {
		Optional<Empleado> optional = empleadoRepository.findByRutdni(empleadoDTO.getRutdni());
		return optional.isPresent();
	}
	
	public List<String[]> procesarSueldoFinal(List<String[]> datas) throws InterruptedException, ExecutionException {
		List<String[]> response = new ArrayList<String[]>();
		ExecutorService executor = Executors.newFixedThreadPool(1000);

	    List<Future<String[]>> futures = new ArrayList<>();

	        for (String[] data : datas) {
	            futures.add(executor.submit(() -> {
	                String[] nuevoData = new String[data.length + 1];		
	        		try {
	        	        System.arraycopy(data, 0, nuevoData, 0, data.length);
	        	        
	        			Long salario   = Long.valueOf(data[4]);
	        			Long bono      = Long.valueOf(data[5]);
	        			Long descuento = Long.valueOf(data[6]);
	        			Long sueldo 	= (salario + bono ) - descuento;
	        			
	        			Empleado empleado = Empleado.builder()
	        					.rutdni(data[0])
	        					.nombre(data[1])
	        					.apellido(data[2])
	        					.cargo(data[3])
	        					.salario(salario)
	        					.bono(bono)
	        					.descuento(descuento)
	        					.build();
	        			empleadoRepository.save(empleado);

	        	        nuevoData[nuevoData.length - 1] = String.valueOf(sueldo);
	        	        
	        	        LOGGER.info("EmpleadoService procesarCSV "+nuevoData);
	        	        
	        		}catch(Exception exception) {
	        	        nuevoData[nuevoData.length - 1] = exception.getMessage();
	        		}
	        		return nuevoData;

	            }));
	        }

	        for (Future<String[]> future : futures) {
	        	response.add(future.get());
	        }
	        executor.shutdown();
	        return response;
	}
	
	public Set<String> validarDatosEntrada(EmpleadoDTO empleadoDTO , Set<String> errores ){
		if(empleadoDTO.getSalario() == null) {
			errores.add("El salario no puede ser  0");
		}
		else if( empleadoDTO.getBono() == null) {
			errores.add("El bono no puede ser  0");
		}
		else if(empleadoDTO.getSalario()/2 < empleadoDTO.getBono()) {
			errores.add("Bonos no pueden superar el 50% del salario base.");
		}
		else if(empleadoDTO.getDescuento() == null ) {
			errores.add("El total de descuentos no puede ser  0");
		}
		else if(empleadoDTO.getDescuento() > empleadoDTO.getSalario() ) {
			errores.add("El total de descuentos no puede ser mayor al salario base.");
		}
		return errores;
	}

	public Empleado guardarEmpleado(EmpleadoDTO empleadoDTO) {
		Empleado empleado = Empleado.builder()
				.nombre(empleadoDTO.getNombre())
				.apellido(empleadoDTO.getApellido())
				.rutdni(empleadoDTO.getRutdni())
				.cargo(empleadoDTO.getCargo())
				.salario(empleadoDTO.getSalario())
				.bono(empleadoDTO.getBono())
				.descuento(empleadoDTO.getDescuento())
				.build();
		return empleadoRepository.save(empleado);
	}

	public List<Empleado> obtenerTodosEmpleados() {
		return empleadoRepository.findAll();
	}

	public boolean eliminarEmpleado(Integer id) {
		if (empleadoRepository.existsById(id)) {
			empleadoRepository.deleteById(id);
			return true;
		} else {
			//throw new RuntimeException("Empleado no encontrado con ID: " + id);
			return false;
		}
	}
}
