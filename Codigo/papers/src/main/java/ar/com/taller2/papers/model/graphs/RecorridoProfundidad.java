package ar.com.taller2.papers.model.graphs;

import java.net.URL;
import java.util.Vector;
import java.util.logging.Logger;

import org.jgrapht.ListenableGraph;
import org.jgrapht.traverse.DepthFirstIterator;

import ar.com.taller2.papers.exceptions.NextStepNotExistsException;
import ar.com.taller2.papers.model.Arista;
import ar.com.taller2.papers.model.Executable;
import ar.com.taller2.papers.model.GraphAlgorithm;
import ar.com.taller2.papers.model.Vertice;

public class RecorridoProfundidad extends GraphAlgorithm implements Executable {

	private ListenableGraph<Vertice, Arista> graph;
	private Vertice inicio;
	private int indiceSiguientePaso;
	private Vector<Vertice> recorrido = new Vector<Vertice>();
	
	
	public RecorridoProfundidad(ListenableGraph<Vertice, Arista> graph, Vertice inicio){
		this.graph = graph;
		this.inicio = inicio;
	}
	
	public void iniciar() {
		DepthFirstIterator<Vertice,Arista> dfit = new DepthFirstIterator<Vertice,Arista>(this.graph, this.inicio);
		while (dfit.hasNext()) {
			this.recorrido.add(dfit.next());
		}
		this.indiceSiguientePaso = 0;
		Logger.getLogger(this.getClass().getName()).info("Inicie el algoritmo");
	}

	public void terminar() {
				
		while(--this.indiceSiguientePaso >= 0) {
			Vertice v = this.recorrido.get(this.indiceSiguientePaso);
			v.select(false);
		}
		this.indiceSiguientePaso = 0;
		Logger.getLogger(this.getClass().getName()).info("Algoritmo finalizado");
	}
	
	public Vertice siguiente() throws NextStepNotExistsException {
		Logger.getLogger(this.getClass().getName()).info("Siguiente");

		if(this.indiceSiguientePaso < this.recorrido.size()) {
			Vertice v = this.recorrido.get(this.indiceSiguientePaso++);
//			v.select(true);
			return v;
		}
		return null;
	}

	public boolean anterior() {
		Logger.getLogger(this.getClass().getName()).info("Anterior");

		if(this.indiceSiguientePaso - 1 >= 0) {
			Vertice v = this.recorrido.get(--this.indiceSiguientePaso);
			v.select(false);
			return true;
		}
		
		return false;
	}
	
	public void principio() {
		Logger.getLogger(this.getClass().getName()).info("Principio");
				
		while(--this.indiceSiguientePaso >= 0) {
			Vertice v = this.recorrido.get(this.indiceSiguientePaso);
			v.select(false);
		}
		
		this.indiceSiguientePaso = 0;
	}
	
	public void fin() {
		Logger.getLogger(this.getClass().getName()).info("Fin");
		
		while(this.indiceSiguientePaso < this.recorrido.size()) {
			Vertice v = this.recorrido.get(this.indiceSiguientePaso++);
			v.select(true);
		}
	}

	public boolean tieneSiguiente() {
		return (this.indiceSiguientePaso < this.recorrido.size());
	}

	public boolean cumpleCondicionesIniciales() {
		return true;
	}

	public String getCondicionesIniciales() {
		return "Este algoritmo no posee condiciones iniciales";
	}

	public URL getAlgoritmo() {
		return this.getClass().getResource("/algorithms/recorrido-profundidad-pseudocodigo.html");
	}

	public String getTitulo() {
		return "Recorrido en Profundidad";
	}

	public URL getDescripcion() {
		return this.getClass().getResource("/algorithms/recorrido-profundidad-info.html");
	}

}
