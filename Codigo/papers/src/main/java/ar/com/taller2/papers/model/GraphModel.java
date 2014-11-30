package ar.com.taller2.papers.model;

import java.util.Iterator;
import java.util.logging.Logger;

import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;
import org.jgrapht.graph.ListenableUndirectedGraph;

import ar.com.taller2.papers.exceptions.CondicionInicialExcepcion;
import ar.com.taller2.papers.exceptions.NextStepNotExistsException;

public class GraphModel {

	private ListenableGraph<Vertice, Arista> graph;
	private int vertice_contador = 0;
	Executable algoritmo;
	
	public GraphModel() {
		
	}
	
	public void nuevoGrafoDirigido() {
		this.graph = new ListenableDirectedGraph<Vertice, Arista>(Arista.class);
		this.agregarVertices();
	}
	
	public void nuevoGrafoNoDirigido() {
		this.graph = new ListenableUndirectedGraph<Vertice, Arista>(Arista.class);		
	}

	public ListenableGraph<Vertice, Arista> getGraph() {
		return graph;
	}
	
	public void setAlgorithm(Executable algoritmo) {
		Logger.getLogger("GraphModel").info("Cambie de algoritmo");
		this.algoritmo = algoritmo;
	}
	
	public Vertice getVertex(String name) {
		Iterator<Vertice> it = this.graph.vertexSet().iterator();
		while (it.hasNext()) {
			Vertice v = it.next();
			if (v.toString() == name) {
				return v;
			}
		}
		return null;
	}
	
	public void startAlgorithm() throws CondicionInicialExcepcion {
		if (algoritmo.cumpleCondicionesIniciales()) {
			algoritmo.iniciar();
		}
		else {
			throw new CondicionInicialExcepcion(algoritmo.getCondicionesIniciales());
		}
	}

	public void stopAlgorithm() {
		algoritmo.terminar();		
	}
	
	public Vertice nextStepAlgorithm() throws NextStepNotExistsException {
		return algoritmo.siguiente();
	}
	
	public void previousStepAlgorithm(){
		algoritmo.anterior();
	}
	
	public void initAlgorithm() {
		algoritmo.principio();
	}
	
	public void endAlgorithm() {
		algoritmo.fin();
	}
	
	public boolean hasNextStepAlgorithm() {
		return algoritmo.tieneSiguiente();
	}
	
	public Vertice agregarVertice(String nombre) {
		Vertice v = new Vertice(nombre, false);
		this.graph.addVertex(v);
		return v;
	}
	
	public Vertice agregarVertice() {
		Vertice v = new Vertice("v" + (++vertice_contador), false);
		this.graph.addVertex(v);
		return v;
	}
	
	public Arista agregarEdge(String source, String dest){
		Vertice v1 = new Vertice(source, false);
		Vertice v2 = new Vertice(dest, false);
		return this.graph.addEdge(v1, v2);
	}
	
	public Arista agregarEdge(Vertice source, Vertice dest){
		return this.graph.addEdge(source, dest);
	}
	
	// TEMP
	public void agregarVertices() {
		
		Vertice v1 = this.agregarVertice();
		Vertice v2 = this.agregarVertice();
		Vertice v3 = this.agregarVertice();
//		Vertice v4 = this.agregarVertice();
//		Vertice v5 = this.agregarVertice();
//		Vertice v6 = this.agregarVertice();
//		Vertice v7 = this.agregarVertice();
//		Vertice v8 = this.agregarVertice();
		
		this.graph.addEdge( v1, v2 );
		this.graph.addEdge( v2, v3);
		this.graph.addEdge(v3, v1);
//		this.graph.addEdge( v1, v4);
//		this.graph.addEdge( v2, v6 );
//		this.graph.addEdge( v4, v5 );
//		this.graph.addEdge( v5, v6);
//        this.graph.addEdge( v6, v7 );
//        this.graph.addEdge( v6, v8 );
//        this.graph.addEdge( v7, v3 );
//        this.graph.addEdge( v8, v3 );
	}
	// END TEMP
}
