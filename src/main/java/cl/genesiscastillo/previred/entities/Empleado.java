package cl.genesiscastillo.previred.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "empleados")
public class Empleado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column
	private String nombre;
	
	@Column
	private String apellido;
	
	@Column
	private String rutdni;
	
	@Column
	private String cargo;
	
	@Column
	private Long salario;
	
	@Column
	private Long bono;
	
	@Column
	private Long descuento;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getRutdni() {
		return rutdni;
	}

	public void setRutdni(String rutdni) {
		this.rutdni = rutdni;
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
	public Empleado() {
		
	}
	private Empleado(Builder builder) {
        this.nombre = builder.nombre;
        this.apellido = builder.apellido;
        this.rutdni = builder.rutdni;
        this.cargo = builder.cargo;
        this.salario = builder.salario;
        this.bono = builder.bono;
        this.descuento = builder.descuento;
    }
	
	public static class Builder {
		private String nombre;
		private String apellido;
		private String rutdni;
		private String cargo;
		private Long salario;
		private Long bono;
		private Long descuento;

        public Builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder apellido(String apellido) {
            this.apellido = apellido;
            return this;
        }
        public Builder rutdni(String rutdni) {
            this.rutdni= rutdni;
            return this;
        }
        public Builder cargo(String cargo) {
            this.cargo= cargo;
            return this;
        }
        
        public Builder salario(Long salario) {
            this.salario = salario;
            return this;
        }
        public Builder bono(Long bono) {
            this.bono = bono;
            return this;
        }
        public Builder descuento(Long descuento) {
            this.descuento = descuento;
            return this;
        }

        public Empleado build() {
            return new Empleado(this);
        }
    }

    // Método estático para obtener una instancia del Builder
    public static Builder builder() {
        return new Builder();
    }	

	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", rutdni=" + rutdni
				+ ", cargo=" + cargo + ", salario=" + salario + ", bono=" + bono + ", descuento=" + descuento + "]";
	}
}
