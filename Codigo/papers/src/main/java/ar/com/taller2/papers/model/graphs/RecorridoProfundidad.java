package ar.com.taller2.papers.model.graphs;

import java.util.Vector;
import java.util.logging.Logger;

import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.DepthFirstIterator;

import ar.com.taller2.papers.model.Executable;
import ar.com.taller2.papers.model.Vertice;

public class RecorridoProfundidad implements Executable {

	private ListenableGraph<Vertice, DefaultEdge> graph;
	private Vertice inicio;
	private int indiceSiguientePaso;
	private Vector<Vertice> recorrido = new Vector<Vertice>();
	
	
	public RecorridoProfundidad(ListenableGraph<Vertice, DefaultEdge> graph, Vertice inicio){
		this.graph = graph;
		this.inicio = inicio;
	}
	
	public void iniciar() {
		DepthFirstIterator<Vertice,DefaultEdge> dfit = new DepthFirstIterator<Vertice,DefaultEdge>(this.graph, this.inicio);
		while (dfit.hasNext()) {
			this.recorrido.add(dfit.next());
		}
		this.indiceSiguientePaso = 0;
		Logger.getLogger("RecorridoProfundidad").info("Inicie el algoritmo");
	}

	public void terminar() {}
	
	public Vertice siguiente() {
		Logger.getLogger("RecorridoProfundidad").info("Siguiente");

		if(this.indiceSiguientePaso < this.recorrido.size()) {
			Vertice v = this.recorrido.get(this.indiceSiguientePaso++);
//			v.select(true);
			return v;
		}
		
		return null;
	}

	public boolean anterior() {
		Logger.getLogger("RecorridoProfundidad").info("Anterior");

		if(this.indiceSiguientePaso - 1 >= 0) {
			Vertice v = this.recorrido.get(--this.indiceSiguientePaso);
			v.select(false);
			return true;
		}
		
		return false;
	}
	
	public void principio() {
		Logger.getLogger("RecorridoProfundidad").info("Principio");
				
		while(--this.indiceSiguientePaso >= 0) {
			Vertice v = this.recorrido.get(this.indiceSiguientePaso);
			v.select(false);
		}
		
		this.indiceSiguientePaso = 0;
	}
	
	public void fin() {
		Logger.getLogger("RecorridoProfundidad").info("Fin");
		
		while(this.indiceSiguientePaso < this.recorrido.size()) {
			Vertice v = this.recorrido.get(this.indiceSiguientePaso++);
			v.select(true);
		}
	}

	public void getEstadoActual() {
		// TODO Auto-generated method stub

	}

	public boolean cumpleCondicionesIniciales() {
		return true;
	}

	public String getCondicionesIniciales() {
		return "Este algoritmo no posee condiciones iniciales";
	}

}