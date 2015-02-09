package ar.com.taller2.papers.model;

import java.util.List;

import ar.com.taller2.papers.exceptions.NextStepNotExistsException;

public interface Executable {
	
	public void iniciar();
	
	public String siguiente() throws NextStepNotExistsException;
	
	public String anterior();
	
	public void terminar();
	
	public String principio();
	
	public String fin();
	
	public boolean cumpleCondicionesIniciales();
	
	public String getCondicionesIniciales();
	
	public boolean tieneSiguiente();
	
	public Boolean isCorrect(Resultado r);
	
	
	/**
	 * Indica si el algoritmo requiere la selección de un vertice origen y uno destino para ser ejecutado(ej Dijsktra)
	 * @return
	 */
	public Boolean isSourceDest();
	
	public void setSource(Vertice v);
	public void setDest(Vertice v);

	public Selectable getCurrentItem();

}
