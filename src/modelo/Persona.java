package modelo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class Persona {
	
	private String nombre;
	private LocalDate nacimiento;
	private Boolean hijoas;
	private int idestudio;
	private int idpersona;
	private String nivelDeEstudios;
	
	public long edad() {
		 LocalDate hoy = LocalDate.now();
		 return ChronoUnit.YEARS.between(nacimiento, hoy); 
	}
	
	public int getIdestudio() {
		return idestudio;
	}
	public void setIdestudios(int idestudio) {
		this.idestudio = idestudio;
	}
	public int getIdpersona() {
		return idpersona;
	}
	public void setIdpersona(int idpersona) {
		this.idpersona = idpersona;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public LocalDate getNacimiento() {
		return nacimiento;
	}
	public void setNacimiento(LocalDate nacimiento) {
		this.nacimiento = nacimiento;
	}
	public Boolean getHijoas() {
		return hijoas;
	}
	public void setHijoas(Boolean hijoas) {
		this.hijoas = hijoas;
	}
	public String getNivelDeEstudios() {
		return nivelDeEstudios;
	}
	public void setNivelDeEstudios(String nivelDeEstudios) {
		this.nivelDeEstudios = nivelDeEstudios;
	}

	
}
