package ar.com.taller2.papers.model;

import ar.com.taller2.papers.exceptions.NextStepNotExistsException;

public interface Executable {
	
	public Vertice siguiente() throws NextStepNotExistsException;
	
	public boolean anterior();
	
	public void iniciar();
	
	public void terminar();
	
	public void principio();
	
	public void fin();
	
	public boolean cumpleCondicionesIniciales();
	
	public String getCondicionesIniciales();
	
	public boolean tieneSiguiente();

}
