package ar.com.taller2.papers.model;

import ar.com.taller2.papers.exceptions.NextStepNotExistsException;

public interface Executable {
	
	public void iniciar();
	
	public Vertice siguiente() throws NextStepNotExistsException;
	
	public boolean anterior();
	
	public void terminar();
	
	public void principio();
	
	public void fin();
	
	public boolean cumpleCondicionesIniciales();
	
	public String getCondicionesIniciales();
	
	public boolean tieneSiguiente();
	
	
	/**
	 * Indica si el algoritmo requiere la selección de un vertice origen y uno destino para ser ejecutado(ej Dijsktra)
	 * @return
	 */
	public Boolean isSourceDest();
	
	public void setSource(Vertice v);
	public void setDest(Vertice v);

}
