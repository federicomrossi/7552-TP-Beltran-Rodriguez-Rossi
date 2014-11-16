package ar.com.taller2.papers.model.graphs;

import java.util.Vector;
import java.util.logging.Logger;

import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.BreadthFirstIterator;

import ar.com.taller2.papers.model.Executable;
import ar.com.taller2.papers.model.Vertice;

public class RecorridoAnchura implements Executable {

	private ListenableGraph<Vertice, DefaultEdge> graph;
	private Vertice inicio;
	private int indiceSiguientePaso;
	private Vector<Vertice> recorrido = new Vector<Vertice>();
	
	
	public RecorridoAnchura(ListenableGraph<Vertice, DefaultEdge> graph, Vertice inicio){
		this.graph = graph;
		this.inicio = inicio;
	}
	
	public void iniciar() {
		BreadthFirstIterator<Vertice,DefaultEdge> dfit = new BreadthFirstIterator<Vertice,DefaultEdge>(this.graph, this.inicio);
		while (dfit.hasNext()) {
			this.recorrido.add(dfit.next());
		}
		this.indiceSiguientePaso = 0;
		Logger.getLogger("RecorridoAnchura").info("Inicie el algoritmo");
	}
	
	public Vertice siguiente() {
		Logger.getLogger("RecorridoAnchura").info("Siguiente");

		if(this.indiceSiguientePaso < this.recorrido.size()) {
			Vertice v = this.recorrido.get(this.indiceSiguientePaso++);
			v.select(true);
			return v;
		}
		
		return null;
	}

	public boolean anterior() {
		Logger.getLogger("RecorridoAnchura").info("Anterior");

		if(this.indiceSiguientePaso - 1 >= 0) {
			Vertice v = this.recorrido.get(--this.indiceSiguientePaso);
			v.select(false);
			return true;
		}
		
		return false;
	}
	
	public void principio() {
		Logger.getLogger("RecorridoAnchura").info("Principio");
				
		while(--this.indiceSiguientePaso >= 0) {
			Vertice v = this.recorrido.get(this.indiceSiguientePaso);
			v.select(false);
		}
		
		this.indiceSiguientePaso = 0;
	}
	
	public void fin() {
		Logger.getLogger("RecorridoAnchura").info("Fin");
		
		while(this.indiceSiguientePaso < this.recorrido.size()) {
			Vertice v = this.recorrido.get(this.indiceSiguientePaso++);
			v.select(true);
		}
	}

	public boolean tieneSiguiente() {
		// TODO Auto-generated method stub
		return true;
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
