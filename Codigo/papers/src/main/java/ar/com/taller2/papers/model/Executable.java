package ar.com.taller2.papers.model;

import java.util.List;

import ar.com.taller2.papers.exceptions.NextStepNotExistsException;

public interface Executable {
	
	public void iniciar();
	
	public void siguiente() throws NextStepNotExistsException;
	
	public boolean anterior();
	
	public void terminar();
	
	public void principio();
	
	public void fin();
	
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
