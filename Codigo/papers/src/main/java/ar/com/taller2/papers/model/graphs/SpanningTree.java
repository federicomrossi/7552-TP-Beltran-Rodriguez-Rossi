package ar.com.taller2.papers.model.graphs;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.jgrapht.alg.KruskalMinimumSpanningTree;
import org.jgrapht.graph.ListenableUndirectedGraph;

import ar.com.taller2.papers.exceptions.NextStepNotExistsException;
import ar.com.taller2.papers.model.Arista;
import ar.com.taller2.papers.model.GraphAlgorithm;
import ar.com.taller2.papers.model.Vertice;

public class SpanningTree extends GraphAlgorithm {

	private ListenableUndirectedGraph<Vertice, Arista> graph;
	private int indiceSiguientePaso = 0;
	private Vertice inicio;
	private List<Arista> spanningTree= null;
	
	
	public SpanningTree(ListenableUndirectedGraph<Vertice, Arista> graph, Vertice inicio){
		this.graph=graph;
		this.inicio=inicio;
	}
	
	public void iniciar() {
		KruskalMinimumSpanningTree<Vertice,Arista> sP = new KruskalMinimumSpanningTree<Vertice,Arista>(graph);
		spanningTree = new ArrayList<Arista>(sP.getMinimumSpanningTreeEdgeSet());
		Logger.getLogger(this.getClass().getSimpleName()).info("Inicié el algoritmo");
	}
	
	public Vertice siguiente() throws NextStepNotExistsException {
		Logger.getLogger(this.getClass().getSimpleName()).info("Siguiente");

		if(this.indiceSiguientePaso < this.spanningTree.size()) {
			Arista v = this.spanningTree.get(this.indiceSiguientePaso++);
			v.select(true);
		}
		return null;
	}

	public boolean anterior() {
		Logger.getLogger(this.getClass().getSimpleName()).info("Anterior");

		if(this.indiceSiguientePaso - 1 >= 0) {
			Arista v = this.spanningTree.get(--this.indiceSiguientePaso);
			v.select(false);
			return true;
		}
		
		return false;
	}

	

	public void terminar() {
		while(--this.indiceSiguientePaso >= 0) {
			Arista v = this.spanningTree.get(this.indiceSiguientePaso);
			v.select(false);
		}
		this.indiceSiguientePaso = 0;
		Logger.getLogger(this.getClass().getSimpleName()).info("Algoritmo finalizado");
	}

	public void principio() {
		Logger.getLogger(this.getClass().getSimpleName()).info("Principio");
		
		while(--this.indiceSiguientePaso >= 0) {
			Arista v = this.spanningTree.get(this.indiceSiguientePaso);
			v.select(false);
		}
		
		this.indiceSiguientePaso = 0;
	}

	public void fin() {
		Logger.getLogger(this.getClass().getSimpleName()).info("Fin");
		
		while(this.indiceSiguientePaso < this.spanningTree.size()) {
			Arista v = this.spanningTree.get(this.indiceSiguientePaso++);
			v.select(true);
		}
	}

	public boolean cumpleCondicionesIniciales() {
		return true;
	}

	public String getCondicionesIniciales() {
		return "Este algoritmo no posee condiciones iniciales";
	}

	public boolean tieneSiguiente() {
		return (this.indiceSiguientePaso < this.spanningTree.size());
	}

	public URL getAlgoritmo() {
		return this.getClass().getResource("/algorithms/arbol-expansion-coste-minimo-pseudocode.html");
	}

	public String getTitulo() {
		return "Arból de expansión de coste mínimo";
	}

	public URL getDescripcion() {
		return this.getClass().getResource("/algorithms/arbol-expansion-coste-minimo-info.html");
	}

	public Boolean isSourceDest() {
		return Boolean.FALSE;
	}

	public void setSource(Vertice v) {
		// TODO Auto-generated method stub
		
	}

	public void setDest(Vertice v) {
		// TODO Auto-generated method stub
		
	}

}
