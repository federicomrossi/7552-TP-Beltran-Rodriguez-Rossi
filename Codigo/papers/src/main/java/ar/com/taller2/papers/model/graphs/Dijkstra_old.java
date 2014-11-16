package ar.com.taller2.papers.model.graphs;

import java.net.URL;

import ar.com.taller2.papers.Main;
import ar.com.taller2.papers.model.GraphAlgorithm;
import ar.com.taller2.papers.model.Vertice;

public class Dijkstra_old extends GraphAlgorithm {

	public Vertice siguiente() {
		// TODO Auto-generated method stub
		return new Vertice("v1", false);
		
	}

	public boolean anterior() {
		// TODO Auto-generated method stub
		return true;
		
	}

	public boolean tieneSiguiente() {
		// TODO Auto-generated method stub
		return true;
	}

	public URL getAlgoritmo() {
		// TODO Auto-generated method stub
		return Dijkstra_old.class.getResource("/algorithms/dijkstra-pseudocode.html");
	}

	public String getTitulo() {
		// TODO Auto-generated method stub
		return "Algoritmo de Dijkstra";
	}

	public URL getDescripcion() {
		// TODO Auto-generated method stub
		return Dijkstra_old.class.getResource("/algorithms/dijkstra-info.html");
	}

	public void iniciar() {
		// TODO Auto-generated method stub
		
	}

	public void principio() {
		// TODO Auto-generated method stub
		
	}

	public void fin() {
		// TODO Auto-generated method stub
		
	}

	public boolean cumpleCondicionesIniciales() {
		// TODO Auto-generated method stub
		return true;
	}

	public String getCondicionesIniciales() {
		// TODO Auto-generated method stub
		return null;
	}

	public void terminar() {
		// TODO Auto-generated method stub
		
	}
	
}
