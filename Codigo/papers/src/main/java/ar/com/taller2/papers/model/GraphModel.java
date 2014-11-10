package ar.com.taller2.papers.model;

import java.util.Iterator;
import java.util.logging.Logger;

import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;
import org.jgrapht.graph.ListenableUndirectedGraph;

import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;

import ar.com.taller2.papers.exceptions.CondicionInicialExcepcion;

public class GraphModel {

	private ListenableGraph<Vertice, DefaultEdge> graph;
	Vertice v1 =new Vertice("v1",false);
	Executable algoritmo;
	
	public GraphModel() {
		
		// TEMP
//		this.agregarVertices();
		// END TEMP
//		algoritmo = new RecorridoProfundidad(graph, v1);
	}
	
	public void nuevoGrafoDirigido() {
		this.graph = new ListenableDirectedGraph<Vertice, DefaultEdge>(DefaultEdge.class);
		this.agregarVertices();
	}
	
	public void nuevoGrafoNoDirigido() {
		this.graph = new ListenableUndirectedGraph<Vertice, DefaultEdge>(DefaultEdge.class);		
	}

	public ListenableGraph<Vertice, DefaultEdge> getGraph() {
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
	
	public Vertice nextStepAlgorithm(){
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
	
	public void agregarVertice(String nombre) {
		Vertice v = new Vertice(nombre, false);
		this.graph.addVertex(v);
	}
	
	// TEMP
	public void agregarVertices() {
		
		Vertice v2 =new Vertice("v2",false);
		Vertice v3 =new Vertice("v3",false);
		Vertice v4 =new Vertice("v4",false);
		Vertice v5 =new Vertice("v5",false);
		Vertice v6 =new Vertice("v6",false);
		Vertice v7 =new Vertice("v7",false);
		Vertice v8 =new Vertice("v8",false);
		
		this.graph.addVertex(v1);
		this.graph.addVertex(v2);
		this.graph.addVertex(v3);
		this.graph.addVertex(v4);
		this.graph.addVertex(v5);
		this.graph.addVertex(v6);
		this.graph.addVertex(v7);
		this.graph.addVertex(v8);
		
		this.graph.addEdge( v1, v2 );
		this.graph.addEdge( v1, v4);
		this.graph.addEdge( v2, v6 );
		this.graph.addEdge( v4, v5 );
		this.graph.addEdge( v5, v6);
        this.graph.addEdge( v6, v7 );
        this.graph.addEdge( v6, v8 );
        this.graph.addEdge( v7, v3 );
        this.graph.addEdge( v8, v3 );
	}
	// END TEMP
}
