package ar.com.taller2.papers.model.graphs;

import java.util.ListIterator;
import java.util.Vector;

import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.DepthFirstIterator;

import ar.com.taller2.papers.model.Executable;
import ar.com.taller2.papers.model.Vertice;

public class RecorridoProfundidad implements Executable {

	private Vector<Vertice> recorrido = new Vector<Vertice>();
	private ListIterator<Vertice> it;
	
	public RecorridoProfundidad(ListenableGraph<Vertice, DefaultEdge> graph, Vertice inicio){
		DepthFirstIterator<Vertice,DefaultEdge> dfit = new DepthFirstIterator<Vertice,DefaultEdge>(graph, inicio);
		while (dfit.hasNext()) {
			this.recorrido.add(dfit.next());			
		}
		this.it = this.recorrido.listIterator();
	}
	
	public Boolean siguiente() {
		if (it.hasNext()){
			Vertice v = it.next();
			v.select(true);
			return true;
		}
		return false;
	}

	public Boolean anterior() {
		if (it.hasPrevious()) {
			Vertice v = it.previous();
			v.select(false);
			return true;
		}
		return false;
	}

	public void getEstadoActual() {
		// TODO Auto-generated method stub

	}

}
