package ar.com.taller2.papers.model.graphs;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.jgrapht.ListenableGraph;
import org.jgrapht.alg.DijkstraShortestPath;

import ar.com.taller2.papers.exceptions.NextStepNotExistsException;
import ar.com.taller2.papers.model.Arista;
import ar.com.taller2.papers.model.GraphAlgorithm;
import ar.com.taller2.papers.model.Vertice;

public class Dijkstra extends GraphAlgorithm {

	private ListenableGraph<Vertice, Arista> graph;
	private Vertice inicio;
	private Vertice fin;
	private int indiceSiguientePaso;
	private List<Arista> camino = new ArrayList<Arista>();
	
	
	public Dijkstra(ListenableGraph<Vertice, Arista> graph){
		this.graph = graph;
	}
	
	public void setSource(Vertice v){
		this.inicio=v;
	}
	
	public void setDest(Vertice v){
		this.fin=v;
	}
	
	public void iniciar() {
		DijkstraShortestPath<Vertice,Arista> dfit = new DijkstraShortestPath<Vertice,Arista>(this.graph, this.inicio, this.fin);
		this.camino = dfit.getPathEdgeList();
		this.indiceSiguientePaso = 0;
		Logger.getLogger("Dijkstra").info("Inicie el algoritmo");
	}
	
	public Vertice siguiente() throws NextStepNotExistsException {
		Logger.getLogger("Dijkstra").info("Siguiente");
		if(this.indiceSiguientePaso < this.camino.size()) {
			Arista v = this.camino.get(this.indiceSiguientePaso++);
			v.select(true);
		}
		return null;
	}

	public boolean anterior() {
		Logger.getLogger("Dijkstra").info("Anterior");

		if(this.indiceSiguientePaso - 1 >= 0) {
			Arista v = this.camino.get(--this.indiceSiguientePaso);
			v.select(false);
			return true;
		}
		
		return false;
	}
	
	public void principio() {
		Logger.getLogger("Dijkstra").info("Principio");
		while(--this.indiceSiguientePaso >= 0) {
			Arista v = this.camino.get(this.indiceSiguientePaso);
			v.select(false);
		}
		
		this.indiceSiguientePaso = 0;
	}
	
	public void fin() {
		Logger.getLogger("Dijkstra").info("Fin");
		while(this.indiceSiguientePaso < this.camino.size()) {
			Arista v = this.camino.get(this.indiceSiguientePaso++);
			v.select(true);
		}
	}

	public boolean tieneSiguiente() {
		return (this.indiceSiguientePaso < this.camino.size());
	}

	public boolean cumpleCondicionesIniciales() {
		return (inicio != null && fin != null);
	}

	public String getCondicionesIniciales() {
		// TODO Auto-generated method stub
		return null;
	}

	public void terminar() {
		while(--this.indiceSiguientePaso >= 0) {
			Arista v = this.camino.get(this.indiceSiguientePaso);
			v.select(false);
		}
		this.indiceSiguientePaso = 0;
	}

	public Boolean isSourceDest() {
		return Boolean.TRUE;
	}

	public URL getAlgoritmo() {
		return this.getClass().getResource("/algorithms/dijkstra-pseudocode.html");
	}

	public String getTitulo() {
		return "Algoritmo de caminos mínimos de Dijsktra";
	}

	public URL getDescripcion() {
		return this.getClass().getResource("/algorithms/dijkstra-info.html");
	}

}
