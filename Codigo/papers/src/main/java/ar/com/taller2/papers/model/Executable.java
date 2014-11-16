package ar.com.taller2.papers.model;

public interface Executable {
	
	public Vertice siguiente();
	
	public boolean anterior();
	
	public void iniciar();
	
	public void terminar();
	
	public void principio();
	
	public void fin();
	
	public boolean cumpleCondicionesIniciales();
	
	public String getCondicionesIniciales();
	
	public boolean tieneSiguiente();

}
