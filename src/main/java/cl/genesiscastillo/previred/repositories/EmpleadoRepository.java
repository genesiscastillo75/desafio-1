package cl.genesiscastillo.previred.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.genesiscastillo.previred.entities.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer>{
	
	 Optional<Empleado> findByRutdni(String rutdni);

}
