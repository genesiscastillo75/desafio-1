package cl.genesiscastillo.previred.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import cl.genesiscastillo.previred.validations.ValidRut;
import cl.genesiscastillo.previred.validations.ValidaSalario;

public class EmpleadoDTO {

	@NotEmpty(message = "Formato rut inválido.")
	@NotBlank(message = "Formato rut inválido.")
	@ValidRut
    String rutdni;
	
	@NotEmpty(message = "Formato nombre inválido.")
	@NotBlank(message = "Formato nombre inválido.")
    String nombre;

	@NotEmpty(message = "Formato apellido inválido.")
	@NotBlank(message = "Formato apellido inválido.")
    String apellido;

	@NotEmpty(message = "Formato cargo inválido.")
	@NotBlank(message = "Formato cargo inválido.")
    String cargo;
	
	@Min(value = 0)
	@ValidaSalario(message = "No permitir salarios base menores a $400,000")
    Long salario;
	
	@Min(value = 0)
    Long bono;

	@Min(value = 0)
    Long descuento;

	public String getRutdni() {
		return rutdni;
	}

	public void setRutdni(String rutdni) {
		this.rutdni = rutdni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Long getSalario() {
		return salario;
	}

	public void setSalario(Long salario) {
		this.salario = salario;
	}

	public Long getBono() {
		return bono;
	}

	public void setBono(Long bono) {
		this.bono = bono;
	}

	public Long getDescuento() {
		return descuento;
	}

	public void setDescuento(Long descuento) {
		this.descuento = descuento;
	}

	@Override
	public String toString() {
		return "EmpleadoDTO [rutdni=" + rutdni + ", nombre=" + nombre + ", apellido=" + apellido + ", cargo=" + cargo
				+ ", salario=" + salario + ", bono=" + bono + ", descuento=" + descuento + "]";
	}

}
