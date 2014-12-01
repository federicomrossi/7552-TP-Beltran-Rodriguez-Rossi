package ar.com.taller2.papers.model.graphs;

import java.net.URL;
import java.util.Vector;

import org.jgrapht.alg.TransitiveClosure;
import org.jgrapht.graph.SimpleDirectedGraph;

import ar.com.taller2.papers.exceptions.NextStepNotExistsException;
import ar.com.taller2.papers.model.Arista;
import ar.com.taller2.papers.model.GraphAlgorithm;
import ar.com.taller2.papers.model.Vertice;

public class CerraduraTransitiva extends GraphAlgorithm {

	private SimpleDirectedGraph<Vertice, Arista> graph;
	private Vertice inicio;
	private int indiceSiguientePaso;
	private Vector<Vertice> recorrido = new Vector<Vertice>();
	
	public CerraduraTransitiva(SimpleDirectedGraph<Vertice, Arista> graph, Vertice inicio){
		this.graph = graph;
		this.inicio = inicio;
	}
	
	public void iniciar() {
		TransitiveClosure tC = TransitiveClosure.INSTANCE;
		tC.closeSimpleDirectedGraph(graph);
	}
	
	
	public Vertice siguiente() throws NextStepNotExistsException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean anterior() {
		// TODO Auto-generated method stub
		return false;
	}

	

	public void terminar() {
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
		return false;
	}

	public String getCondicionesIniciales() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean tieneSiguiente() {
		// TODO Auto-generated method stub
		return false;
	}

	public URL getAlgoritmo() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTitulo() {
		// TODO Auto-generated method stub
		return null;
	}

	public URL getDescripcion() {
		// TODO Auto-generated method stub
		return null;
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
