package ar.com.taller2.papers.model.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;

import org.jgrapht.ListenableGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;

import ar.com.taller2.papers.exceptions.NextStepNotExistsException;
import ar.com.taller2.papers.model.Executable;
import ar.com.taller2.papers.model.Vertice;

public class Dijkstra implements Executable {

	private ListenableGraph<Vertice, DefaultEdge> graph;
	private Vertice inicio;
	private Vertice fin;
	private int indiceSiguientePaso;
	private Vector<Vertice> recorrido = new Vector<Vertice>();
	private List<DefaultEdge> camino = new ArrayList<DefaultEdge>();
	
	
	public Dijkstra(ListenableGraph<Vertice, DefaultEdge> graph, Vertice inicio, Vertice fin){
		this.graph = graph;
		this.inicio = inicio;
		this.fin = fin;
	}
	
	public void iniciar() {
		DijkstraShortestPath<Vertice,DefaultEdge> dfit = new DijkstraShortestPath<Vertice,DefaultEdge>(this.graph, this.inicio, this.fin);
		this.camino = dfit.getPathEdgeList();
		
		this.indiceSiguientePaso = 0;
		Logger.getLogger("Dijkstra").info("Inicie el algoritmo");
	}
	
	public Vertice siguiente() throws NextStepNotExistsException {
		Logger.getLogger("Dijkstra").info("Siguiente");

//		if(this.indiceSiguientePaso < this.recorrido.size()) {
//			Vertice v = this.recorrido.get(this.indiceSiguientePaso++);
//			v.select(true);
//			return true;
//		}
		
		return inicio;
	}

	public boolean anterior() {
		Logger.getLogger("Dijkstra").info("Anterior");

//		if(this.indiceSiguientePaso - 1 >= 0) {
//			Vertice v = this.recorrido.get(--this.indiceSiguientePaso);
//			v.select(false);
//			return true;
//		}
		
		return false;
	}
	
	public void principio() {
		Logger.getLogger("Dijkstra").info("Principio");
				
//		while(--this.indiceSiguientePaso >= 0) {
//			Vertice v = this.recorrido.get(this.indiceSiguientePaso);
//			v.select(false);
//		}
//		
//		this.indiceSiguientePaso = 0;
	}
	
	public void fin() {
		Logger.getLogger("Dijkstra").info("Fin");
		
//		while(this.indiceSiguientePaso < this.recorrido.size()) {
//			Vertice v = this.recorrido.get(this.indiceSiguientePaso++);
//			v.select(true);
//		}
	}

	public boolean tieneSiguiente() {
		return true;
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
