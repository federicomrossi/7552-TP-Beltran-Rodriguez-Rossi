package ar.com.taller2.papers.model.graphs;

import java.util.ListIterator;
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
	private Vector<Vertice> recorrido = new Vector<Vertice>();
	private ListIterator<Vertice> it;
	
	public RecorridoProfundidad(ListenableGraph<Vertice, DefaultEdge> graph, Vertice inicio){
		this.graph = graph;
		this.inicio = inicio;
	}
	
	public void iniciar() {
		DepthFirstIterator<Vertice,DefaultEdge> dfit = new DepthFirstIterator<Vertice,DefaultEdge>(this.graph, this.inicio);
		while (dfit.hasNext()) {
			this.recorrido.add(dfit.next());			
		}
		this.it = this.recorrido.listIterator();		
		Logger.getLogger("RecorridoProfundidad").info("Inicie el algoritmo");
	}
	
	public Boolean siguiente() {
		Logger.getLogger("RecorridoProfundidad").info("Siguiente");
		if (it.hasNext()){
			Vertice v = it.next();
			v.select(true);
			return true;
		}
		return false;
	}

	public Boolean anterior() {
		Logger.getLogger("RecorridoProfundidad").info("anterior");
		if (it.hasPrevious()) {
			Vertice v = it.previous();
			v.select(false);
			return true;
		}
		return false;
	}
	
	public void principio() {
		while (it.hasPrevious()) {
			Vertice v = it.previous();
			v.select(false);			
		}
	}
	
	public void fin() {
		while (it.hasNext()) {
			Vertice v = it.next();
			v.select(true);	
			Logger.getLogger("RecorridoProfundidad").info(v.toString());
		}		
	}

	public void getEstadoActual() {
		// TODO Auto-generated method stub

	}

}
