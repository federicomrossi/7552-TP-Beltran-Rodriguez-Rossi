package ar.com.taller2.papers.model.graphs;

import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.ClosestFirstIterator;
import org.jgrapht.traverse.DepthFirstIterator;

import ar.com.taller2.papers.model.Executable;
import ar.com.taller2.papers.model.Vertice;

public class RecorridoProfundidad implements Executable {

	private ListenableGraph<Vertice, DefaultEdge> graph;
	private DepthFirstIterator<Vertice,DefaultEdge> it;
	
	public RecorridoProfundidad(ListenableGraph<Vertice, DefaultEdge> graph){
		this.graph = graph;
		this.it = new DepthFirstIterator<Vertice,DefaultEdge>(graph);
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
		return true;

	}

	public void getEstadoActual() {
		// TODO Auto-generated method stub

	}

}
